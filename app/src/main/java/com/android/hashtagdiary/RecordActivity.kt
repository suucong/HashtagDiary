package com.android.hashtagdiary

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecordActivity : AppCompatActivity() {

    lateinit var ll_weather : LinearLayout
    lateinit var ll_mood : LinearLayout
    lateinit var ll_food : LinearLayout
    lateinit var ll_meet : LinearLayout
    lateinit var ll_meet_detail : LinearLayout

    lateinit var rg_meet : RadioGroup

    lateinit var tvToday : TextView
    lateinit var btnNext : Button
    lateinit var rdobtnMeetYes : RadioButton
    lateinit var rdobtnMeetNo : RadioButton

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        ll_weather = findViewById(R.id.linearLayout_weather)
        ll_mood = findViewById(R.id.linearLayout_mood)
        ll_food = findViewById(R.id.linearLayout_food)
        ll_meet = findViewById(R.id.linearLayout_meet)
        ll_meet_detail = findViewById(R.id.linearLayout_meetDetail)

        rg_meet = findViewById(R.id.rgMeet)

        tvToday = findViewById(R.id.tvToday)
        btnNext = findViewById(R.id.btnNext)
        rdobtnMeetYes = findViewById(R.id.rdobtnMeetYes)
        rdobtnMeetNo = findViewById(R.id.rdobtnMeetNo)

        var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
        tvToday.text = "오늘은 $today 입니다."

        btnNext.setOnClickListener {
            if (ll_mood.isVisible == false && ll_food.isVisible == false && ll_meet.isVisible == false) {
                ll_mood.setVisibility(View.VISIBLE)
            }
            else if (ll_food.isVisible == false && ll_meet.isVisible == false) {
                ll_food.setVisibility(View.VISIBLE)
            }
            else {
                ll_meet.setVisibility(View.VISIBLE)
            }
        }

//        while (true) {
            if (rdobtnMeetYes.isChecked) {
                ll_meet_detail.setVisibility(View.VISIBLE)
            }
            if (rdobtnMeetNo.isChecked) {
                ll_meet_detail.setVisibility(View.GONE)
            }
  //      }

    }
}