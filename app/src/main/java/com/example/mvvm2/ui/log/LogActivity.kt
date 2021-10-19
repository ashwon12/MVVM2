package com.example.mvvm2.ui.log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.base.BaseActivity
import com.example.mvvm2.databinding.ActivityLogBinding
import com.example.mvvm2.ui.adapter.LogRecyclerAdapter
import com.example.mvvm2.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogActivity : BaseActivity<ActivityLogBinding>(R.layout.activity_log) {

    private val logViewModel: LogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initRecycler()

        logViewModel.logList()
    }

    private fun initBinding() {
        binding.viewmodel = logViewModel
    }

    private fun initRecycler() {
        binding.rvLog.adapter = LogRecyclerAdapter {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("query", it)
            }
            setResult(1004, intent)
            finish()
        }
    }
}