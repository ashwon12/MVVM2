package com.example.mvvm2.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.base.BaseActivity
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail){
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailItem = intent?.extras?.getParcelable<ItemX>("detailItem")

        initBinding()

        detailItem?.let { detailViewModel.updateItem(it) } ?: run {
            showToast("올바르지 못한 접근입니다.")
            finish()
        }
    }

    private fun initBinding() {
        binding.detailViewModel = detailViewModel
    }

}