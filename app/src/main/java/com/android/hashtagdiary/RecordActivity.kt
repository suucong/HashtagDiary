package com.android.hashtagdiary

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecordActivity : AppCompatActivity() {

    // layout
    lateinit var ll_sleep : LinearLayout
    lateinit var ll_weather : LinearLayout
    lateinit var ll_mood : LinearLayout
    lateinit var ll_food : LinearLayout
    lateinit var ll_meet : LinearLayout
    lateinit var ll_meet_detail : LinearLayout

    lateinit var rg_meet : RadioGroup

    lateinit var tvToday : TextView
    lateinit var edtFood : EditText
    lateinit var edtMeetWho : EditText
    lateinit var edtMeetWhere : EditText
    lateinit var btnNext : Button
    lateinit var btnResult : Button

    // weather
    lateinit var chkbxSunny : CheckBox
    lateinit var chkbxBetween : CheckBox
    lateinit var chkbxCloudy : CheckBox
    lateinit var chkbxRain : CheckBox
    lateinit var chkbxSnow : CheckBox
    lateinit var chkbxForgot : CheckBox

    // mood
    lateinit var chkbxHappy : CheckBox
    lateinit var chkbxComfort : CheckBox
    lateinit var chkbxLethargic : CheckBox
    lateinit var chkbxGloomy : CheckBox
    lateinit var chkbxLonely : CheckBox
    lateinit var chkbxDontknow : CheckBox

    // sleep
    lateinit var rdobtnSleepGood : RadioButton
    lateinit var rdobtnSleepBad : RadioButton

    // food
    lateinit var rdobtnBest : RadioButton
    lateinit var rdobtnGood : RadioButton
    lateinit var rdobtnSoso : RadioButton
    lateinit var rdobtnBad : RadioButton
    lateinit var rdobtnWorst : RadioButton

    // meet
    lateinit var rdobtnMeetYes : RadioButton
    lateinit var rdobtnMeetNo : RadioButton

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        ll_sleep = findViewById(R.id.linearLayout_sleep)
        ll_weather = findViewById(R.id.linearLayout_weather)
        ll_mood = findViewById(R.id.linearLayout_mood)
        ll_food = findViewById(R.id.linearLayout_food)
        ll_meet = findViewById(R.id.linearLayout_meet)
        ll_meet_detail = findViewById(R.id.linearLayout_meetDetail)

        rg_meet = findViewById(R.id.rgMeet)

        tvToday = findViewById(R.id.tvToday)
        edtFood = findViewById(R.id.edtFood)
        edtMeetWho = findViewById(R.id.edtMeetWho)
        edtMeetWhere = findViewById(R.id.edtMeetWhere)
        btnNext = findViewById(R.id.btnNext)
        btnResult = findViewById(R.id.btnResult)

        // weather
        chkbxSunny = findViewById(R.id.chkbxSunny)
        chkbxBetween = findViewById(R.id.chkbxBetween)
        chkbxCloudy = findViewById(R.id.chkbxCloudy)
        chkbxRain = findViewById(R.id.chkbxRain)
        chkbxSnow = findViewById(R.id.chkbxSnow)
        chkbxForgot = findViewById(R.id.chkbxForgot)

        // mood
        chkbxHappy = findViewById(R.id.chkbxHappy)
        chkbxComfort = findViewById(R.id.chkbxComfort)
        chkbxLethargic = findViewById(R.id.chkbxLethargic)
        chkbxGloomy = findViewById(R.id.chkbxGloomy)
        chkbxLonely = findViewById(R.id.chkbxLonely)
        chkbxDontknow = findViewById(R.id.chkbxDontknow)

        // sleep
        rdobtnSleepGood = findViewById(R.id.rdobtnSleepGood)
        rdobtnSleepBad = findViewById(R.id.rdobtnSleepBad)

        // food
        rdobtnBest = findViewById(R.id.rdobtnBest)
        rdobtnGood = findViewById(R.id.rdobtnGood)
        rdobtnSoso = findViewById(R.id.rdobtnSoso)
        rdobtnBad= findViewById(R.id.rdobtnBad)
        rdobtnWorst= findViewById(R.id.rdobtnWorst)

        // meet
        rdobtnMeetYes = findViewById(R.id.rdobtnMeetYes)
        rdobtnMeetNo = findViewById(R.id.rdobtnMeetNo)

        var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
        tvToday.text = "오늘은 $today 입니다."

        btnNext.setOnClickListener {
            if (ll_weather.isVisible == false && ll_mood.isVisible == false && ll_food.isVisible == false && ll_meet.isVisible == false) {
                ll_weather.setVisibility(View.VISIBLE)
            }
            else if (ll_mood.isVisible == false && ll_food.isVisible == false && ll_meet.isVisible == false) {
                ll_mood.setVisibility(View.VISIBLE)
            }
            else if (ll_food.isVisible == false && ll_meet.isVisible == false) {
                ll_food.setVisibility(View.VISIBLE)
            }
            else {
                ll_meet.setVisibility(View.VISIBLE)
                btnNext.setVisibility(View.GONE)
                btnResult.setVisibility(View.VISIBLE)
            }
        }

        rdobtnMeetYes.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked == true) {
                ll_meet_detail.setVisibility(View.VISIBLE)
            }
            else {
                ll_meet_detail.setVisibility(View.GONE)
            }
        }

        btnResult.setOnClickListener {
            var intentResult = Intent(this, ResultActivity::class.java)
            if ((rdobtnSleepGood.isChecked || rdobtnSleepBad.isChecked) == false) {
                Toast.makeText(this, "편안한 잠을 잤는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((chkbxSunny.isChecked || chkbxBetween.isChecked || chkbxCloudy.isChecked
                        || chkbxRain.isChecked || chkbxSnow.isChecked || chkbxForgot.isChecked) == false) {
                Toast.makeText(this, "오늘의 날씨를 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((chkbxHappy.isChecked || chkbxComfort.isChecked || chkbxLethargic.isChecked
                        || chkbxGloomy.isChecked || chkbxLonely.isChecked || chkbxDontknow.isChecked) == false) {
                Toast.makeText(this, "오늘의 기분을 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((rdobtnBest.isChecked || rdobtnGood.isChecked || rdobtnSoso.isChecked
                        || rdobtnBad.isChecked || rdobtnWorst.isChecked) == false) {
                Toast.makeText(this, "오늘 먹은 음식이 어땠는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((rdobtnMeetYes.isChecked || rdobtnMeetNo.isChecked) == false) {
                Toast.makeText(this, "오늘 만남이 있었는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                if (rdobtnSleepGood.isChecked) {
                    intentResult.putExtra("sleep" , "잠을 잘 잔 것")
                }
                else if (rdobtnSleepBad.isChecked) {
                    intentResult.putExtra("sleep", "잠을 잘 자지 못한 것")
                }
                if (chkbxSunny.isChecked) {
                    intentResult.putExtra("weather1", "맑음")
                }
                if (chkbxBetween.isChecked) {
                    intentResult.putExtra("weather2", "약간 흐림")
                }
                if (chkbxCloudy.isChecked) {
                    intentResult.putExtra("weather3", "흐림")
                }
                if (chkbxRain.isChecked) {
                    intentResult.putExtra("weather4", "비")
                }
                if (chkbxSnow.isChecked) {
                    intentResult.putExtra("weather5", "눈")
                }
                if (chkbxForgot.isChecked) {
                    intentResult.putExtra("weather6", "기억 안남")
                }
                if (chkbxHappy.isChecked) {
                    intentResult.putExtra("mood1", "행복")
                }
                if (chkbxComfort.isChecked) {
                    intentResult.putExtra("mood2", "편안")
                }
                if (chkbxLethargic.isChecked) {
                    intentResult.putExtra("mood3", "무기력")
                }
                if (chkbxGloomy.isChecked) {
                    intentResult.putExtra("mood4", "우울")
                }
                if (chkbxLonely.isChecked) {
                    intentResult.putExtra("mood5", "쓸쓸")
                }
                if (chkbxDontknow.isChecked) {
                    intentResult.putExtra("mood6", "모르겠음")
                }
                if (edtFood.text.equals("") == false) {
                    intentResult.putExtra("edtfood", edtFood.text)
                }
                if (rdobtnBest.isChecked) {
                    intentResult.putExtra("food", "매일 먹고 싶은")
                }
                else if (rdobtnGood.isChecked) {
                    intentResult.putExtra("food", "그럭저럭 맜있는")
                }
                else if (rdobtnSoso.isChecked) {
                    intentResult.putExtra("food", "무난한")
                }
                else if (rdobtnBad.isChecked) {
                    intentResult.putExtra("food", "맛없는")
                }
                else if (rdobtnWorst.isChecked) {
                    intentResult.putExtra("food", "다신 안먹고 싶은")
                }
                if (rdobtnMeetYes.isChecked) {
                    intentResult.putExtra("meet", "약속이 있는")
                    if (edtMeetWho.equals("") == false) {
                        intentResult.putExtra("meetwho", edtMeetWho.text)
                    }
                    if (edtMeetWhere.equals("") == false) {
                        intentResult.putExtra("meetwhere", edtMeetWhere.text)
                    }
                }
                else if (rdobtnMeetNo.isChecked) {
                    intentResult.putExtra("meet", "약속이 없는")
                }
            }

         //   startActivity(intentResult)
        }
    }
}