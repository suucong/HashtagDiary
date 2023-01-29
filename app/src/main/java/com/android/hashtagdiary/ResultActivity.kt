package com.android.hashtagdiary

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var tvLine1 : TextView  // date
    lateinit var tvLine2 : TextView  // sleep
    lateinit var tvLine3 : TextView  // weather
    lateinit var tvLine4 : TextView  // food
    lateinit var tvLine5 : TextView  // meet
    lateinit var tvLine6 : TextView  // mood
    lateinit var tvHashtag : TextView  // hashtag

    lateinit var map_View: ConstraintLayout
    lateinit var mapView: MapView
    private val ACCESS_FINE_LOCATION = 1000

    lateinit var btnDiarytab : Button

    var latitude : Double = 0.0
    var longtitude : Double = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val fragment_Diary = DiaryFragment()

        map_View = findViewById(R.id.map_View)

        dbManager = DBManager(this, "diarybyday", null, 1)

        tvLine1 = findViewById(R.id.tvLine1)
        tvLine2 = findViewById(R.id.tvLine2)
        tvLine3 = findViewById(R.id.tvLine3)
        tvLine4 = findViewById(R.id.tvLine4)
        tvLine5 = findViewById(R.id.tvLine5)
        tvLine6 = findViewById(R.id.tvLine6)
        tvHashtag = findViewById(R.id.tvHashTag)

        btnDiarytab = findViewById(R.id.btnDiarytab)

        val tvToday = intent.getStringExtra("tvToday")
        val sleep = intent.getStringExtra("sleep")
        val mood = intent.getStringExtra("mood")
        val weather = intent.getStringExtra("weather")
        val edtFood = intent.getStringExtra("edtfood")
        val food = intent.getStringExtra("food")
        val meet = intent.getStringExtra("meet")
        val meetWho = intent.getStringExtra("edtmeetwho")
        val meetWhere = intent.getStringExtra("edtmeetwhere")

        tvLine1.text = "오늘은 ${tvToday},"

        val dateString = tvToday.toString()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
        val date = LocalDate.parse(dateString, formatter)

        // sleep
        if (sleep == "잘잤음") {
            tvLine2.text = "잠을 잘 잤기 때문에, 일어났을 때 꽤 개운했다."
        }
        else if (sleep == "못잤음") {
            tvLine2.text = "잠을 잘 못잤기 때문에, 찌부둥한 상태로 일어나게 되었다."
        }

        // weather
        if (weather.equals("맑음")) {
            tvLine3.text = "오늘의 날씨는 매우 맑고 햇살이 따사롭게 내리쬐는 날씨였다."
        }
        else if (weather == "약간_흐림") {
            tvLine3.text = "오늘은 약간 흐려서, 눈이 편했고 바깥활동을 하기 좋은 날씨였다."
        }
        else if (weather == "흐림") {
            tvLine3.text = "오늘은 꽤나 흐려서, 마치 곧 비가 올 것처럼 구름이 가득했다."
        }
        else if (weather == "비") {
            tvLine3.text = "오늘은 비가 계속 내려서, 하루종일 어두컴컴하고 습도가 높은 날씨였다."
        }
        else if (weather == "눈") {
            tvLine3.text = "오늘은 눈이 내려서 온 세상이 하얗고, 곳곳에 눈사람이 보이는 하루였다."
        }
        else if (weather == "날씨_기억안남") {
            tvLine3.text = "오늘은 꽤나 정신없이 살았는지 날씨가 기억이 나질 않는다."
        }

        // food
        if (food.equals("매일_먹고싶음")) {
             tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 매일 먹고 싶을 만큼 정말 맛있었다."
        }
        else if (food.equals("맛있음")) {
            tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 맛있었고 꽤 괜찮았다."
        }
        else if (food == "무난함") {
            tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 그럭저럭 무난한 맛이었다."
        }
        else if (food == "맛없음") {
            tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 맛이 없어서 조금 실망스러웠다..."
        }
        else if (food == "다신_안먹고싶음") {
            tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 다신 안먹고 싶을만큼 최악의 맛이었다🤢"
        }
        else if (food == "무슨맛인지_기억_안남") {
            tvLine4.text = "그리고 오늘 ${edtFood}을(를) 먹었는데, 무슨 맛인지 기억이 나질 않는다. 딱히 인상적이진 않았나보다."
        }

        // meet
        if (meet == "약속있음") {
            tvLine5.text = "또, 오늘은 ${meetWho}와(과) 약속이 있었어서, ${meetWhere}에서 만남을 가졌다."
        }
        else if (meet == "약속없음") {
            tvLine5.text = "오늘은 별다른 약속이 없었기 때문에 나만의 시간을 보냈다."
        }

        // mood
        if (mood == "행복") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나는 행복했다고 말할 수 있을 것 같다."
        }
        else if (mood == "편안") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나는 편안한 하루를 보냈다고 말할 수 있을 것 같다.."
        }
        else if (mood == "무기력") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나의 하루는 조금 무기력했다고 말할 수 있을 것 같다."
        }
        else if (mood == "우울") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나는 조금 우울했다고 말할 수 있을 것 같다."
        }
        else if (mood == "쓸쓸") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나는 쓸쓸한 감정을 많이 느꼈다고 말할 수 있을 것 같다."
        }
        else if (mood == "모르겠음") {
            tvLine6.text = "전체적으로 하루를 되돌아보았을 때, 오늘 나의 감정은 하나로 정의내리기가 어려운 것 같다."
        }

        // hashtag
        if (meet == "약속있음") {
            tvHashtag.text =
                "#$sleep #$weather #$mood #${edtFood}_${food} #${meetWhere}_with_${meetWho}"
        } else if (meet == "약속없음") {
            tvHashtag.text = "#$sleep #$weather #$mood #${edtFood}_${food}"
        }

        mapView = MapView(this)
        map_View.addView(mapView)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )

            // 권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 이전에 거부한 적이 있으면 설명(참고)
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("위치 정보를 얻기 위해서는 위치 권한이 필수로 필요합니다.")
                dlg.setPositiveButton("확인") {dialog, which -> ActivityCompat.requestPermissions(this@ResultActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)}
                dlg.setNegativeButton("취소", null)
                dlg.show()
            } else {
                // 처음 권한 요청
                ActivityCompat.requestPermissions(this@ResultActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)

            } else {
            // 권한이 이미 제대로 허용됨
            mapView.currentLocationTrackingMode =
                MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading  //이 부분

            val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            //위도 , 경도
            val uLatitude = userNowLocation?.latitude
            val uLongitude = userNowLocation?.longitude

            latitude = uLatitude!!
            longtitude = uLongitude!!

            val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)

            mapView.setMapCenterPoint(uNowPosition, true)
            mapView.setZoomLevel(1, true)

            // 현 위치에 마커 찍기
            val marker = MapPOIItem()
            marker.itemName = "현 위치"
            marker.mapPoint =uNowPosition
            marker.markerType = MapPOIItem.MarkerType.BluePin
            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
            mapView.addPOIItem(marker)
        }

        sqlitedb = dbManager.writableDatabase
        sqlitedb.execSQL("INSERT INTO diarybyday VALUES ('"+ date +"', '"+ tvLine2.text.toString() +"', '"
                + tvLine3.text.toString() +"', '"+ tvLine4.text.toString() +"', '"+ tvLine5.text.toString() +"', '"
                +tvLine6.text.toString() +"', '"+ tvHashtag.text.toString() +"', '"+ latitude +"', '"+ longtitude +"');")
        sqlitedb.close()

        btnDiarytab.setOnClickListener {
            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }
    }
}


