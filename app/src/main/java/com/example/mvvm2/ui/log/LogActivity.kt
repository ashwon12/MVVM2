package com.example.mvvm2.ui.log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.databinding.ActivityLogBinding
import com.example.mvvm2.ui.adapter.LogRecyclerAdapter
import com.example.mvvm2.ui.main.MainActivity

class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding
    private lateinit var logViewModel: LogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initBinding()
        initRecycler()

        logViewModel.logList()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log)
        binding.viewmodel = logViewModel
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        logViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            LogViewModel::class.java)
    }

    private fun initRecycler() {
        binding.rvLog.adapter = LogRecyclerAdapter {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("query",it)
            }
            startActivity(intent)
        }
    }
}