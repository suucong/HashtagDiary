package com.android.hashtagdiary

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.hashtagdiary.databinding.ActivityNaviBinding

class NaviActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaviBinding
    private val ACCESS_FINE_LOCATION = 1000

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

        // 위치 권한 요청하는 코드
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )

        // 권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 이전에 거부한 적이 있으면 설명(참고)
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("위치 정보를 얻기 위해서는 위치 권한이 필수로 필요합니다.")
                dlg.setPositiveButton("확인") {dialog, which -> ActivityCompat.requestPermissions(this@NaviActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)}
                dlg.setNegativeButton("취소", null)
                dlg.show()
            } else {
                // 처음 권한 요청
                ActivityCompat.requestPermissions(this@NaviActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)

            } else {
            // 권한이 이미 제대로 허용됨
        }
    }

    private fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}