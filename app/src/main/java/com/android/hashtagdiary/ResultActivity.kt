package com.android.hashtagdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import net.daum.mf.map.api.MapView

class ResultActivity : AppCompatActivity() {
    lateinit var tvLine1 : TextView
    lateinit var tvLine2 : TextView
    lateinit var tvLine3 : TextView
    lateinit var tvHashtag : TextView
    lateinit var map_View: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        map_View = findViewById(R.id.map_view)

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

        val mapView = MapView(this)
        map_View.addView(mapView)
    }
}