package com.example.mvvm2.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.json.JSONArray

object SharedPref {
    private lateinit var sharedPreference:SharedPreferences
    private val editor  = sharedPreference.edit()
    private const val prefId: String = "wodnjs"

    fun setPref(context: Context) {
        context.getSharedPreferences(prefId, MODE_PRIVATE)
    }

    fun saveLog(searchList : ArrayList<String>){
        editor.putString("searchLog", JSONArray(searchList).toString()).apply()
    }

    fun getLog() = sharedPreference.getString("searchLog",null)
}