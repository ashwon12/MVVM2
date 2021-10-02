package com.example.mvvm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.viewmodel.MovieViewModel
import com.example.mvvm2.viewmodel.MovieViewModelIpl

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter : RecyclerAdapter
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewModel 객체 생성
        movieViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(MovieViewModel::class.java)
        movieAdapter = RecyclerAdapter()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
        binding.rvMovie.adapter = movieAdapter

    }
}