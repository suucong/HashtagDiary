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
    lateinit var chkbxAngry : CheckBox
    lateinit var chkbxSad : CheckBox
    lateinit var chkbxDontknow : CheckBox

    // food
    lateinit var chkbxBest : CheckBox
    lateinit var chkbxGood : CheckBox
    lateinit var chkbxSoso : CheckBox
    lateinit var chkbxBad : CheckBox
    lateinit var chkbxWorst : CheckBox
    lateinit var chkbxForgotfood : CheckBox

    // sleep
    lateinit var rdobtnSleepGood : RadioButton
    lateinit var rdobtnSleepBad : RadioButton

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
        chkbxAngry = findViewById(R.id.chkbxAngry)
        chkbxSad = findViewById(R.id.chkbxSad)
        chkbxDontknow = findViewById(R.id.chkbxDontknow)

        // food
        chkbxBest = findViewById(R.id.chkbxBest)
        chkbxGood = findViewById(R.id.chkbxGood)
        chkbxSoso = findViewById(R.id.chkbxSoso)
        chkbxBad= findViewById(R.id.chkbxBad)
        chkbxWorst= findViewById(R.id.chkbxWorst)
        chkbxForgotfood = findViewById(R.id.chkbxForgotfood)

        // sleep
        rdobtnSleepGood = findViewById(R.id.rdobtnSleepGood)
        rdobtnSleepBad = findViewById(R.id.rdobtnSleepBad)

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

        // 날씨 하나만 선택
        chkbxSunny.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxSunny.isChecked) {
                chkbxBetween.setChecked(false)
                chkbxCloudy.setChecked(false)
                chkbxRain.setChecked(false)
                chkbxSnow.setChecked(false)
                chkbxForgot.setChecked(false)
            }
        }

        chkbxBetween.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxBetween.isChecked) {
                chkbxSunny.setChecked(false)
                chkbxCloudy.setChecked(false)
                chkbxRain.setChecked(false)
                chkbxSnow.setChecked(false)
                chkbxForgot.setChecked(false)
            }
        }

        chkbxCloudy.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxCloudy.isChecked) {
                chkbxSunny.setChecked(false)
                chkbxBetween.setChecked(false)
                chkbxRain.setChecked(false)
                chkbxSnow.setChecked(false)
                chkbxForgot.setChecked(false)
            }
        }

        chkbxRain.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxRain.isChecked) {
                chkbxSunny.setChecked(false)
                chkbxBetween.setChecked(false)
                chkbxCloudy.setChecked(false)
                chkbxSnow.setChecked(false)
                chkbxForgot.setChecked(false)
            }
        }

        chkbxSnow.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxSnow.isChecked) {
                chkbxSunny.setChecked(false)
                chkbxBetween.setChecked(false)
                chkbxCloudy.setChecked(false)
                chkbxRain.setChecked(false)
                chkbxForgot.setChecked(false)
            }
        }

        chkbxForgot.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxForgot.isChecked) {
                chkbxSunny.setChecked(false)
                chkbxBetween.setChecked(false)
                chkbxCloudy.setChecked(false)
                chkbxRain.setChecked(false)
                chkbxSnow.setChecked(false)
            }
        }

        // 기분 하나만 선택
        chkbxHappy.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxHappy.isChecked) {
                chkbxComfort.setChecked(false)
                chkbxLethargic.setChecked(false)
                chkbxAngry.setChecked(false)
                chkbxSad.setChecked(false)
                chkbxDontknow.setChecked(false)
            }
        }

        chkbxComfort.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxComfort.isChecked) {
                chkbxHappy.setChecked(false)
                chkbxLethargic.setChecked(false)
                chkbxAngry.setChecked(false)
                chkbxSad.setChecked(false)
                chkbxDontknow.setChecked(false)
            }
        }

        chkbxLethargic.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxLethargic.isChecked) {
                chkbxHappy.setChecked(false)
                chkbxComfort.setChecked(false)
                chkbxAngry.setChecked(false)
                chkbxSad.setChecked(false)
                chkbxDontknow.setChecked(false)
            }
        }

        chkbxAngry.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxAngry.isChecked) {
                chkbxHappy.setChecked(false)
                chkbxComfort.setChecked(false)
                chkbxLethargic.setChecked(false)
                chkbxSad.setChecked(false)
                chkbxDontknow.setChecked(false)
            }
        }

        chkbxSad.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxSad.isChecked) {
                chkbxHappy.setChecked(false)
                chkbxComfort.setChecked(false)
                chkbxLethargic.setChecked(false)
                chkbxAngry.setChecked(false)
                chkbxDontknow.setChecked(false)
            }
        }

        chkbxDontknow.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxDontknow.isChecked) {
                chkbxHappy.setChecked(false)
                chkbxComfort.setChecked(false)
                chkbxLethargic.setChecked(false)
                chkbxAngry.setChecked(false)
                chkbxSad.setChecked(false)
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

        // 음식 하나만 선택
        chkbxBest.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxBest.isChecked) {
                chkbxGood.setChecked(false)
                chkbxSoso.setChecked(false)
                chkbxBad.setChecked(false)
                chkbxWorst.setChecked(false)
                chkbxForgotfood.setChecked(false)
            }
        }

        chkbxGood.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxGood.isChecked) {
                chkbxBest.setChecked(false)
                chkbxSoso.setChecked(false)
                chkbxBad.setChecked(false)
                chkbxWorst.setChecked(false)
                chkbxForgotfood.setChecked(false)
            }
        }

        chkbxSoso.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxSoso.isChecked) {
                chkbxBest.setChecked(false)
                chkbxGood.setChecked(false)
                chkbxBad.setChecked(false)
                chkbxWorst.setChecked(false)
                chkbxForgotfood.setChecked(false)
            }
        }

        chkbxBad.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxBad.isChecked) {
                chkbxBest.setChecked(false)
                chkbxGood.setChecked(false)
                chkbxSoso.setChecked(false)
                chkbxWorst.setChecked(false)
                chkbxForgotfood.setChecked(false)
            }
        }

        chkbxWorst.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxWorst.isChecked) {
                chkbxBest.setChecked(false)
                chkbxGood.setChecked(false)
                chkbxSoso.setChecked(false)
                chkbxBad.setChecked(false)
                chkbxForgotfood.setChecked(false)
            }
        }

        chkbxForgotfood.setOnCheckedChangeListener { button, isChecked ->
            if (chkbxForgotfood.isChecked) {
                chkbxBest.setChecked(false)
                chkbxGood.setChecked(false)
                chkbxSoso.setChecked(false)
                chkbxBad.setChecked(false)
                chkbxWorst.setChecked(false)
            }
        }

        // 완료 버튼 클릭 이벤트
        btnResult.setOnClickListener {
            var intentResult = Intent(this, ResultActivity::class.java)
            intentResult.putExtra("tvToday", today)
            if ((rdobtnSleepGood.isChecked || rdobtnSleepBad.isChecked) == false) {
                Toast.makeText(this, "편안한 잠을 잤는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((chkbxSunny.isChecked || chkbxBetween.isChecked || chkbxCloudy.isChecked
                        || chkbxRain.isChecked || chkbxSnow.isChecked || chkbxForgot.isChecked) == false) {
                Toast.makeText(this, "오늘의 날씨를 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((chkbxHappy.isChecked || chkbxComfort.isChecked || chkbxLethargic.isChecked
                        || chkbxAngry.isChecked || chkbxSad.isChecked || chkbxDontknow.isChecked) == false) {
                Toast.makeText(this, "오늘의 기분을 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if (edtFood.length() == 0) {
                Toast.makeText(this, "오늘 무슨 음식을 먹었는지 작성하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((chkbxBest.isChecked || chkbxGood.isChecked || chkbxSoso.isChecked
                        || chkbxBad.isChecked || chkbxWorst.isChecked || chkbxForgotfood.isChecked) == false) {
                Toast.makeText(this, "오늘 먹은 음식이 어땠는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else if ((rdobtnMeetYes.isChecked || rdobtnMeetNo.isChecked) == false) {
                Toast.makeText(this, "오늘 만남이 있었는지 선택하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                // 잠
                if (rdobtnSleepGood.isChecked) {
                    intentResult.putExtra("sleep" , "잘잤음")
                }
                else if (rdobtnSleepBad.isChecked) {
                    intentResult.putExtra("sleep", "못잤음")
                }
                else {}

                // 날씨
                if (chkbxSunny.isChecked) {
                    intentResult.putExtra("weather", "맑음")
                }
                else if (chkbxBetween.isChecked) {
                    intentResult.putExtra("weather", "약간_흐림")
                }
                else if (chkbxCloudy.isChecked) {
                    intentResult.putExtra("weather", "흐림")
                }
                else if (chkbxRain.isChecked) {
                    intentResult.putExtra("weather", "비")
                }
                else if (chkbxSnow.isChecked) {
                    intentResult.putExtra("weather", "눈")
                }
                else if (chkbxForgot.isChecked) {
                    intentResult.putExtra("weather", "날씨_기억_안남")
                }
                else {}

                // 기분
                if (chkbxHappy.isChecked) {
                    intentResult.putExtra("mood", "행복")
                }
                else if (chkbxComfort.isChecked) {
                    intentResult.putExtra("mood", "편안")
                }
                else if (chkbxLethargic.isChecked) {
                    intentResult.putExtra("mood", "무기력")
                }
                else if (chkbxAngry.isChecked) {
                    intentResult.putExtra("mood", "화남")
                }
                else if (chkbxSad.isChecked) {
                    intentResult.putExtra("mood", "슬픔")
                }
                else if (chkbxDontknow.isChecked) {
                    intentResult.putExtra("mood", "모르겠음")
                }
                else {}

                // 음식 종류
                if (edtFood.text.length != 0) {
                    intentResult.putExtra("edtfood", edtFood.text.toString())
                }
                else {}

                //음식
                if (chkbxBest.isChecked) {
                    intentResult.putExtra("food", "매일_먹고싶음")
                }
                else if (chkbxGood.isChecked) {
                    intentResult.putExtra("food", "맛있음")
                }
                else if (chkbxSoso.isChecked) {
                    intentResult.putExtra("food", "무난함")
                }
                else if (chkbxBad.isChecked) {
                    intentResult.putExtra("food", "맛없음")
                }
                else if (chkbxWorst.isChecked) {
                    intentResult.putExtra("food", "다신_안먹고싶음")
                }
                else if (chkbxForgotfood.isChecked) {
                    intentResult.putExtra("food", "무슨맛인지_기억_안남")
                }
                else {}

                // 만남
                if (rdobtnMeetYes.isChecked) {
                    if (edtMeetWho.length() == 0 && edtMeetWhere.length() == 0) {
                        Toast.makeText(this, "누구와 어디서 만났는지 작성하세요", Toast.LENGTH_SHORT).show()
                    }
                    else if (edtMeetWho.length() == 0) {
                        Toast.makeText(this, "누구와 만났는지 작성하세요", Toast.LENGTH_SHORT).show()
                    }
                    else if (edtMeetWhere.length() == 0) {
                        Toast.makeText(this, "${edtMeetWho.text}와(과) 어디서 만났는지 작성하세요", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        intentResult.putExtra("meet", "약속있음")
                        intentResult.putExtra("edtmeetwho", edtMeetWho.text.toString())
                        intentResult.putExtra("edtmeetwhere", edtMeetWhere.text.toString())
                        startActivity(intentResult)
                    }
                }
                else if (rdobtnMeetNo.isChecked) {
                    intentResult.putExtra("meet", "약속없음")
                    startActivity(intentResult)
                }
                else {}
            }
        }
    }
}