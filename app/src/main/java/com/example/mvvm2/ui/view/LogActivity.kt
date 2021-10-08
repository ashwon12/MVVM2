package com.example.mvvm2.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        logRecyclerAdapter = LogRecyclerAdapter()
        binding.rvLog.adapter = logRecyclerAdapter
        binding.rvLog.layoutManager = LinearLayoutManager(this)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log)
       logViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LogViewModel::class.java)
       logViewModel.logList.observe(this, Observer {
           binding.viewmodel = logViewModel
           binding.lifecycleOwner = this
       })
    }
}