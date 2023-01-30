package com.android.hashtagdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StoryActivity : AppCompatActivity() {
    lateinit var btnToGoHome : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        btnToGoHome = findViewById<Button>(R.id.btnToGoHome)

        btnToGoHome.setOnClickListener {
            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }
    }
}