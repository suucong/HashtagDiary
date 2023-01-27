package com.android.hashtagdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint

class ResultActivity : AppCompatActivity() {
    lateinit var map_View: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        map_View = findViewById(R.id.map_view)

        val mapView = MapView(this)
        map_View.addView(mapView)
    }
}

