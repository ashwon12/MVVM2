package com.example.mvvm2.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.json.JSONArray

object SharedPref {
    private lateinit var sharedPreference:SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private const val prefId: String = "wodnjs"

    fun setPref(context: Context) {
        sharedPreference = context.getSharedPreferences(prefId, MODE_PRIVATE)
        editor= sharedPreference.edit()
    }

    fun saveLog(searchList : ArrayList<String>){
        editor.putString("searchLog", JSONArray(searchList).toString()).apply()
    }

    fun getLog() = sharedPreference.getString("searchLog",null)
}