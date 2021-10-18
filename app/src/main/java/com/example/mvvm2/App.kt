package com.example.mvvm2

import android.app.Application
import com.example.mvvm2.data.local.SharedPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.setPref(this)
    }
}