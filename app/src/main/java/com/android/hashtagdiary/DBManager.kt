package com.android.hashtagdiary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager (
    context: Context?,
    name : String?,
    factory : SQLiteDatabase.CursorFactory?,
    version : Int
) : SQLiteOpenHelper (context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE diarybyday (date Date, sleep text, weather text, food text, meet text, mood text, hashtag text, latitude real, longtitude real)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}