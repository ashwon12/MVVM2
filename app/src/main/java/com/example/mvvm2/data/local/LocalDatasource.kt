package com.example.mvvm2.data.local

import android.util.Log
import com.example.mvvm2.App
import org.json.JSONArray

interface LocalDataSource {
    fun getLog() : ArrayList<String>
    fun saveLog(keyword: String)
}

class LocalDataSourceImpl : LocalDataSource {
    override fun getLog(): ArrayList<String> {
        val savedList = App.sharedPrefs.getLog()
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
        App.sharedPrefs.saveLog(savedList)

        Log.d("LocalDataSource","save success : $keyword")
        Log.d("LocalDataSource","recent list : $savedList")
    }
}