package com.example.mvvm2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.R
import com.example.mvvm2.ui.adapter.MovieRecyclerAdapter
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.ui.detail.DetailActivity
import com.example.mvvm2.ui.log.LogActivity

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initRecycler()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieViewModel.showToast.observe(this) {
            showToast(it)
        }
    }

    private fun initRecycler() {
        binding.rvMovie.adapter = MovieRecyclerAdapter(){
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("detailItem",it)
            }
            startActivity(intent)
        }
    }

    fun showLogActivity() {
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }
}