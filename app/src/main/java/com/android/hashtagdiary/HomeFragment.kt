package com.android.hashtagdiary

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate

class HomeFragment : Fragment() {
    lateinit var btnRecord : Button
    lateinit var btnTellStory : Button

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnRecord = view.findViewById(R.id.btnRecord)
        btnTellStory = view.findViewById(R.id.btnTellStory)
        dbManager = DBManager(requireContext(), "diarybyday", null, 1)

        sqlitedb = dbManager.readableDatabase
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT date FROM diarybyday;", null)
        var today = LocalDate.now().toString()

        var plag : String = "기록 안함"
        btnRecord.setOnClickListener {
            while (cursor.moveToNext()) {
                // 오늘 이미 일기를 기록하였을 때
                if (today.equals(cursor.getString(0).toString())) {
                    Toast.makeText(requireContext(), "이미 오늘의 일기가 기록되었습니다.", Toast.LENGTH_SHORT).show()
                    plag = "기록 완료"
                    break
                }
                else{
                }
            }

            //  오늘 쓴 일기가 없을 때
            if (plag.equals("기록 안함")) {
                var intentRecord = Intent(getActivity(), RecordActivity::class.java)
                startActivity(intentRecord)
                sqlitedb.close()
                cursor.close()
            }

            plag = "기록 안함"
            cursor.moveToFirst()

        }

        // 버튼 누르면, 스토리텔링 액티비티로 전환
        btnTellStory.setOnClickListener {
            var intent = Intent(getActivity(), StoryActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}