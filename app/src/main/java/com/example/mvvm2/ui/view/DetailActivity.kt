package com.example.mvvm2.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm2.R
import com.example.mvvm2.data.dto.ItemX

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailItem = intent?.extras?.getParcelable<ItemX>("detailItem")
        Log.d("DetailActivity", "clicked : $detailItem")
    }
}