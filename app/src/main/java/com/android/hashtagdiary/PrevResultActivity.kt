package com.android.hashtagdiary

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_prev_result.*
import net.daum.mf.map.api.MapView

class PrevResultActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var tvLine1_prev : TextView  // date
    lateinit var tvLine2_prev : TextView  // sleep
    lateinit var tvLine3_prev : TextView  // weather
    lateinit var tvLine4_prev : TextView  // food
    lateinit var tvLine5_prev : TextView  // meet
    lateinit var tvLine6_prev : TextView  // mood
    lateinit var tvHashtag_prev : TextView  // hashtag

    lateinit var map_View_prev : ConstraintLayout
    lateinit var mapView : MapView
    private val ACCESS_FINE_LOCATION = 1000

    lateinit var btnBack_prev : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_result)

        dbManager = DBManager(this, "diarybyday", null , 1)

        tvLine1_prev = findViewById(R.id.tvLine1_prev)
        tvLine2_prev = findViewById(R.id.tvLine2_prev)
        tvLine3_prev = findViewById(R.id.tvLine3_prev)
        tvLine4_prev = findViewById(R.id.tvLine4_prev)
        tvLine5_prev = findViewById(R.id.tvLine5_prev)
        tvLine6_prev = findViewById(R.id.tvLine6_prev)
        tvHashtag_prev = findViewById(R.id.tvHashTag_prev)

        btnBack_prev = findViewById(R.id.btnBack_prev)

        sqlitedb = dbManager.readableDatabase
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT date FROM diarybyday;", null)

        while (cursor.moveToNext()) {
        }
    }
}