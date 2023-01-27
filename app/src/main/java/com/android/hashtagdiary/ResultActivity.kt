package com.android.hashtagdiary

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint

class ResultActivity : AppCompatActivity() {
    lateinit var tvDate: TextView

    lateinit var tvLine1 : TextView
    lateinit var tvLine2 : TextView
    lateinit var tvLine3 : TextView
    lateinit var tvHashtag : TextView

    lateinit var map_View: ConstraintLayout
    lateinit var mapView: MapView
    private val ACCESS_FINE_LOCATION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        map_View = findViewById(R.id.map_View)

        tvLine1 = findViewById(R.id.tvLine1)
        tvLine2 = findViewById(R.id.tvLine2)
        tvLine3 = findViewById(R.id.tvLine3)
        tvHashtag = findViewById(R.id.tvHashTag)

        val tvToday = intent.getStringExtra("tvToday")
        val sleep = intent.getStringExtra("sleep")
        val mood = intent.getStringExtra("mood")
        val weather = intent.getStringExtra("weather")
        val edtFood = intent.getStringExtra("edtfood")
        val food = intent.getStringExtra("food")
        val meet = intent.getStringExtra("meet")
        val meetWho = intent.getStringExtra("meetwho")
        val meetWhere = intent.getStringExtra("meetwhere")

        tvLine1.text = "오늘은 ${tvToday},"

        if (sleep == "잘잤음") {
            tvLine2.text = "잠을 잘 잤기 때문에, 일어났을 때 꽤 개운했다."
        }
        else if (sleep == "못잤음") {
            tvLine2.text = "잠을 잘 못잤기 때문에, 찌부둥한 상태로 일어나게 되었다."
        }

        if (meet == "약속있음") {
            tvHashtag.text = "#$sleep #$weather #$mood #${edtFood}_${food} #${meetWhere}_with_${meetWho}"
        }
        else if (meet == "약속없음") {
            tvHashtag.text = "#$sleep #$weather #$mood #${edtFood}_${food}"
        }

        mapView = MapView(this)
        map_View.addView(mapView)

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)

        // 권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 이전에 거부한 적이 있으면 설명(참고)
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
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
    }
}

