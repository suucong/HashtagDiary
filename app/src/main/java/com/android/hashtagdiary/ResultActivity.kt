package com.android.hashtagdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var tvDate : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvDate = findViewById(R.id.tvDate)

        val tvToday = intent.getStringExtra("tvToday")

        tvDate.text = "오늘은 ${tvToday}"

    }
}