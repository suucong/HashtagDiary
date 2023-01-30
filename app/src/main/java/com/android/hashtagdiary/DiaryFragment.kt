package com.android.hashtagdiary

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat

class DiaryFragment : Fragment() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var calendarDiary : CalendarView
    lateinit var btnPrevResult : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_diary, container, false)

        dbManager = DBManager(requireContext(), "diarybyday", null, 1)

        calendarDiary = view.findViewById(R.id.calendarDiary)
        btnPrevResult = view.findViewById(R.id.btnPrevResult)
        
        var dateFormat : DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date : String = dateFormat.format(calendarDiary.date)
        
        calendarDiary.setOnDateChangeListener { calendarDiary, year, month, dayOfMonth ->
            if (month in 0..8)
                date = "${year}-0${month+1}-${dayOfMonth}"
            else
                date = "${year}-${month+1}-${dayOfMonth}"
        }

        sqlitedb = dbManager.readableDatabase
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT date FROM diarybyday;", null)

        btnPrevResult.setOnClickListener {
            var flag : String = "기록 없음"
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals(date)) {
                    var intentPrevResult = Intent(getActivity(), PrevResultActivity::class.java)
                    intentPrevResult.putExtra("date", date)
                    startActivity(intentPrevResult)
                    flag = "기록 있음"
                    break
                }
            }

            if (flag.equals("기록 없음")) {
                Toast.makeText(requireContext(), "선택한 날짜에 기록된 일기가 없습니다.", Toast.LENGTH_SHORT).show()
            }
            sqlitedb.close()
            cursor.close()
        }

        return view
    }
}