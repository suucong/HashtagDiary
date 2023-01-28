package com.android.hashtagdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.hashtagdiary.databinding.ActivityNaviBinding

class NaviActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaviBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_fragment -> changeFragment(HomeFragment())
                R.id.diary_fragment -> changeFragment(DiaryFragment())
                R.id.setting_fragment -> changeFragment(SettingFragment())
            }
            true
        }
    }

    private fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}