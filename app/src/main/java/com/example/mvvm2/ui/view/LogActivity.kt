package com.example.mvvm2.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm2.R
import com.example.mvvm2.databinding.ActivityLogBinding
import com.example.mvvm2.ui.adapter.LogRecyclerAdapter
import com.example.mvvm2.viewmodel.LogViewModel
import com.example.mvvm2.viewmodel.MovieViewModel

class LogActivity : AppCompatActivity() {
    private lateinit var logViewModel: LogViewModel
    private lateinit var logRecyclerAdapter: LogRecyclerAdapter
    private lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initRecyler()
        logViewModel.getSearchLogList()
    }

    private fun initRecyler() {
        logRecyclerAdapter = LogRecyclerAdapter(){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("query",it)
            }
            startActivity(intent)
        }
        binding.rvLog.adapter = logRecyclerAdapter
        binding.rvLog.layoutManager = LinearLayoutManager(this)
    }

    private fun initBinding() {
        logViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LogViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log)
        binding.viewmodel = logViewModel
        binding.lifecycleOwner = this
    }
}