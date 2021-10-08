package com.example.mvvm2.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ActivityDetailBinding
import com.example.mvvm2.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initBinding()

        val detailItem = intent?.extras?.getParcelable<ItemX>("detailItem")
        Log.d("DetailActivity", "clicked : $detailItem")
        detailItem?.let { detailViewModel.updateItem(it) }
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailViewModel::class.java)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this
    }
}