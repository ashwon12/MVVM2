package com.example.mvvm2.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailItem = intent?.extras?.getParcelable<ItemX>("detailItem")

        initViewModel()
        initBinding()

        detailItem?.let { detailViewModel.updateItem(it) } ?: run {
            Toast.makeText(applicationContext, "올바르지 않은 접근입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailViewModel::class.java)
    }
}