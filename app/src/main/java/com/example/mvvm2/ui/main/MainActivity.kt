package com.example.mvvm2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.mvvm2.R
import com.example.mvvm2.base.BaseActivity
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.ui.adapter.MovieRecyclerAdapter
import com.example.mvvm2.ui.detail.DetailActivity
import com.example.mvvm2.ui.log.LogActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var searchLogKeyword : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
        initRecycler()

        searchLogKeyword = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == 1004) { // 로그 키워드 전달 받았을 때
                val logQuery = it.data?.getStringExtra("query")
                logQuery?.let { query ->
                    movieViewModel.searchByLog(query)
                }
            }
        }
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = movieViewModel
    }

    private fun initViewModel() {
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
        searchLogKeyword.launch(intent)
    }
}