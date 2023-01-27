package com.android.hashtagdiary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPoint

class ResultActivity : AppCompatActivity() {
    lateinit var tvDate : TextView
    lateinit var map_View: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        map_View = findViewById(R.id.map_view)

        tvDate = findViewById(R.id.tvDate)

        val tvToday = intent.getStringExtra("tvToday")

        tvDate.text = "오늘은 ${tvToday}"

        val mapView = MapView(this)
        map_View.addView(mapView)
    }
}