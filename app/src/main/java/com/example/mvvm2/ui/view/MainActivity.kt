package com.example.mvvm2.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm2.R
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.ui.adapter.RecyclerAdapter
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter : RecyclerAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initRecyler()
    }

    private fun initRecyler() {
        movieAdapter = RecyclerAdapter(){
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("detailItem",it)
            }
            startActivity(intent)
        }
        binding.rvMovie.adapter = movieAdapter
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
    }

    private fun initBinding() {
        movieViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(MovieViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
    }
}