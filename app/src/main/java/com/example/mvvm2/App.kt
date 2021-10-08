package com.example.mvvm2

import android.app.Application
import com.example.mvvm2.data.local.SharedPreference

class App : Application() {
    companion object{
        lateinit var sharedPrefs: SharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = SharedPreference(applicationContext)
    }
}