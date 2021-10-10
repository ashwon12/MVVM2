package com.example.mvvm2

import android.app.Application
import com.example.mvvm2.data.local.SharedPreference

class App : Application() {
    companion object{
        lateinit var sharedPrefs: SharedPreference
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPrefs = SharedPreference(applicationContext)
    }
}