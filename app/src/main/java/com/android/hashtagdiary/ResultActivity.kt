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
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var imgToday : ImageView

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

        map_View = findViewById(R.id.map_View)

        dbManager = DBManager(this, "diarybyday", null, 1)

        imgToday = findViewById(R.id.imgToday)

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
            tvLine2.text = "오늘 너는 잠을 잘 자서, 개운하게 일어난 것 같았어."
        }
        else if (sleep == "못잤음") {
            tvLine2.text = "오늘 너는 잠을 잘 못 자서, 찌부둥해 보였어."
        }

        // weather
        if (weather.equals("맑음")) {
            imgToday.setImageResource(R.drawable.img_result_sunny)
            tvLine3.text = "바깥은 매우 맑고, 햇살이 따사롭게 내리쬐는 날씨였어."
        }
        else if (weather == "약간_흐림") {
            imgToday.setImageResource(R.drawable.img_result_cloud)
            tvLine3.text = "바깥은 약간 흐려서, 편안하게 외출하기에 좋은 날씨였어."
        }
        else if (weather == "흐림") {
            imgToday.setImageResource(R.drawable.img_result_vcloud)
            tvLine3.text = "바깥은 엄청 흐려서, 곧 비가 올 것처럼 구름이 가득했어."
        }
        else if (weather == "비") {
            imgToday.setImageResource(R.drawable.img_result_rain)
            tvLine3.text = "바깥은 비가 계속 내려서, 하루 종일 어두컴컴하고 끈적거리는 날씨였어."
        }
        else if (weather == "눈") {
            imgToday.setImageResource(R.drawable.img_result_snow)
            tvLine3.text = "바깥은 눈이 내려서, 온 세상이 하얗고 곳곳에 눈사람이 보이는 하루였어."
        }
        else if (weather == "날씨_기억안남") {
            imgToday.setImageResource(R.drawable.img_result)
            tvLine3.text = "날씨는 기억나지 않는다고 했어. 너는 오늘 아주 바빴나봐."
        }

        // food
        if (food.equals("매일_먹고싶음")) {
             tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 매일 먹고 싶을 만큼 정말 맛있었대."
        }
        else if (food.equals("맛있음")) {
            tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 제법 맛있었대."
        }
        else if (food == "무난함") {
            tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 그럭저럭 무난한 맛이었대."
        }
        else if (food == "맛없음") {
            tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 맛이 없어서 조금 실망스러웠대."
        }
        else if (food == "다신_안먹고싶음") {
            tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 다신 안먹고 싶을만큼 최악의 맛이었대..."
        }
        else if (food == "무슨맛인지_기억_안남") {
            tvLine4.text = "그리고 ${edtFood}을(를) 먹었는데, 인상적이진 않았나봐. 어떤 맛인지 기억이 안 난대."
        }

        // meet
        if (meet == "약속있음") {
            tvLine5.text = "오늘은 ${meetWho}와(과) 약속이 있어서, ${meetWhere}에 다녀왔어."
        }
        else if (meet == "약속없음") {
            tvLine5.text = "오늘은 별다른 약속이 없어서, 혼자만의 시간을 보냈어."
        }

        // mood
        if (mood == "행복") {
            tvLine6.text = "잠자리에 누워서 말하길, 너는 행복한 하루를 보냈다고 했어. 내일도 오늘만 같기를 바랄게."
        }
        else if (mood == "편안") {
            tvLine6.text = "잠자리에 누워서 말하길, 너는 편안한 하루를 보냈다고 했어. 네가 행복해 보여서 기뻐."
        }
        else if (mood == "무기력") {
            tvLine6.text = "잠자리에 누워서 말하길, 너의 하루는 조금 무기력했대. 그래도 내일 너는 더 행복할거야."
        }
        else if (mood == "화남") {
            tvLine6.text = "잠자리에 누워서 말하길, 너는 조금 화가 났었대. 내일은 그러지 않기를 기도할게."
        }
        else if (mood == "슬픔") {
            tvLine6.text = "잠자리에 누워서 말하길, 너는 슬픈 것 같았대. 괜찮아, 내가 옆에 있을게."
        }
        else if (mood == "모르겠음") {
            tvLine6.text = "잠자리에 누워서 말하길, 너의 하루는 너무 복잡했대. 내일은 더 행복하길 바랄게."
        }

        // hashtag
        if (meet == "약속있음") {
            tvHashtag.text =
                "#$sleep #$weather #$mood #${edtFood}_${food} #${meetWhere}_with_${meetWho}"
        } else if (meet == "약속없음") {
            tvHashtag.text = "#$sleep #$weather #$mood #${edtFood}_${food}"
        }

        // map_View 레이아웃에 지도 띄우기
        mapView = MapView(this)
        map_View.addView(mapView)

        // 위치 권한이 허용되어 있어야지만, 사용자의 현재 위치를 받아올 수 있음
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

            //현재 위도, 경도 받아오기
            val uLatitude = userNowLocation?.latitude
            val uLongitude = userNowLocation?.longitude

            // 변수에 위도, 경도 저장
            latitude = uLatitude!!
            longtitude = uLongitude!!

            val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)

            // 현재위치가 있는 화면으로 지도가 나오도록 함
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

        // 데이터 베이스에 결과화면 저장
        sqlitedb = dbManager.writableDatabase
        sqlitedb.execSQL("INSERT INTO diarybyday VALUES ('"+ date +"', '"+ tvLine2.text.toString() +"', '"
                + tvLine3.text.toString() +"', '"+ tvLine4.text.toString() +"', '"+ tvLine5.text.toString() +"', '"
                +tvLine6.text.toString() +"', '"+ tvHashtag.text.toString() +"', '"+ latitude +"', '"+ longtitude +"');")
        sqlitedb.close()

        // 결과화면에서 다시 홈화면으로
        btnDiarytab.setOnClickListener {
            val intentNavi = Intent(this, NaviActivity::class.java)
            startActivity(intentNavi)
        }
    }
}


