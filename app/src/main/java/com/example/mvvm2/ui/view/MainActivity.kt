package com.example.mvvm2.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm2.R
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.ui.adapter.MovieRecyclerAdapter
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.viewmodel.MovieViewModel
import com.example.mvvm2.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity(){

    private lateinit var movieAdapterMovie : MovieRecyclerAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initRecyler()

        val query = intent?.extras?.getString("query")
        query?.run {
            Log.d("MainActivity", this)
            movieViewModel.query.set(this)
            movieViewModel.getSearchResponseList()
        }
    }

    private fun initRecyler() {
        movieAdapterMovie = MovieRecyclerAdapter(){
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("detailItem",it)
            }
            startActivity(intent)
        }
        binding.rvMovie.adapter = movieAdapterMovie
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
    }

    private fun initBinding() {
        movieViewModel = ViewModelProvider(this,MovieViewModelFactory(){
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        }).get(MovieViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
    }
}