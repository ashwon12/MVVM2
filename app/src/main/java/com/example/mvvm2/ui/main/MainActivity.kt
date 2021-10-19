package com.example.mvvm2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.mvvm2.R
import com.example.mvvm2.data.local.LocalDataSourceImpl
import com.example.mvvm2.data.remote.RemoteDatasourceIpl
import com.example.mvvm2.data.repository.MovieRepositoryIpl
import com.example.mvvm2.ui.adapter.MovieRecyclerAdapter
import com.example.mvvm2.databinding.ActivityMainBinding
import com.example.mvvm2.ui.detail.DetailActivity
import com.example.mvvm2.ui.log.LogActivity

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        movieViewModel = MovieViewModel(MovieRepositoryIpl(RemoteDatasourceIpl(),
            LocalDataSourceImpl(applicationContext.getSharedPreferences("searchLog", MODE_PRIVATE))
        ))
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

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }
}