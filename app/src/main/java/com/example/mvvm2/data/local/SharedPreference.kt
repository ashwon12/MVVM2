package com.example.mvvm2.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.mvvm2.data.dto.ItemX
import org.json.JSONArray

class SharedPreference(context : Context) {

    private val sharedPreference =context.getSharedPreferences("searchLog",MODE_PRIVATE)
    private val editor  = sharedPreference.edit()

    fun saveLog(searchList : ArrayList<String>){
        editor.putString("searchLog",JSONArray(searchList).toString()).apply()
    }

    fun getLog() = sharedPreference.getString("searchLog",null)
}