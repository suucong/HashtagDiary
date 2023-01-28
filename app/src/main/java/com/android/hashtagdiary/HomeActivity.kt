package com.android.hashtagdiary

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.hashtagdiary.databinding.ActivityHomeBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.security.MessageDigest
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var btnRecord : Button

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 지도 API 위한 해시키 찾는 함수
//        getAppKeyHash()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_diary, R.id.navigation_setting
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        btnRecord = findViewById(R.id.btnRecord)

        btnRecord.setOnClickListener {
            var intentRecord = Intent(this, RecordActivity::class.java)
            startActivity(intentRecord)
        }
    }

    // 해시키 찾는 함수
    private fun getAppKeyHash() {
        try {
            val info =
                packageManager.getPackageInfo("com.android.hashtagdiary", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {

            Log.e("name not found", e.toString())
        }
    }
}

