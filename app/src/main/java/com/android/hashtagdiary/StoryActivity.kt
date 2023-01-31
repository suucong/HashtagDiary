package com.android.hashtagdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StoryActivity : AppCompatActivity() {

    lateinit var btnToGoHome : Button  // <기록하러 가기> 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_story)

        btnToGoHome = findViewById<Button>(R.id.btnToGoHome)

        // <기록하러 가기> 버튼 클릭 이벤트
        btnToGoHome.setOnClickListener {
            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }
    }
}