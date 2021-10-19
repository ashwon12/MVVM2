package com.example.mvvm2.data.local

import android.content.SharedPreferences
import android.util.Log
import org.json.JSONArray
import javax.inject.Inject

interface LocalDataSource {
    fun getLog() : ArrayList<String>
    fun saveLog(keyword: String)
}


class LocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : LocalDataSource {
    private val editor  = sharedPreferences.edit()

    override fun getLog(): ArrayList<String> {
        val savedList = sharedPreferences.getString("searchLog",null)
        val logList = arrayListOf<String>()

        savedList?.run {
            val jsonArray = JSONArray(this)
            for (i in 0 until jsonArray.length() ){
                logList.add(jsonArray.optString(i).toString())
            }
        }
        return logList
    }

    override fun saveLog(keyword: String) {
        val savedList = getLog()
        if(savedList.contains(keyword)){ // 중복 체크
            savedList.remove(keyword)
        }
        savedList.add(0,keyword)
        editor.putString("searchLog", JSONArray(savedList).toString()).apply()
    }
}