package com.example.mvvm2

import android.app.Application
import com.example.mvvm2.data.local.SharedPreference

class App : Application() {
    private lateinit var sharedPrefs: SharedPreference

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = SharedPreference(applicationContext)
    }

    fun getInstance(): App {
        return this;
    }

    fun getShared(): SharedPreference {
        return sharedPrefs;
    }
}