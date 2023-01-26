package com.android.hashtagdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var btnRecord : Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRecord = findViewById(R.id.btnRecord)

        btnRecord.setOnClickListener {
            var intentRecord = Intent(this, RecordActivity::class.java)
            startActivity(intentRecord)
        }

    }
}