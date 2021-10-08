package com.example.mvvm2.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.App
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.data.dto.SearchResponse

import com.example.mvvm2.data.repository.MovieRepositoryIpl

import retrofit2.Call
import retrofit2.Response


interface MovieViewModelIpl {
    val movieList : LiveData<ArrayList<ItemX>>
    val logList : LiveData<ArrayList<String>>
    fun getSearchResponseList()
    fun getSearchLogList()
}

class MovieViewModel() : ViewModel(), MovieViewModelIpl {
    private val repository : MovieRepositoryIpl = MovieRepositoryIpl()

    val query = ObservableField<String>()
    private val _movieList : MutableLiveData<ArrayList<ItemX>> = MutableLiveData()
    override val movieList: LiveData<ArrayList<ItemX>>
        get() = _movieList

    private val _logList : MutableLiveData<ArrayList<String>> = MutableLiveData()
    override val logList: LiveData<ArrayList<String>>
        get() = _logList

    override fun getSearchResponseList() {
        //검색 기록 저장하기
        repository.saveSearchLog(query.get().toString())

        //입력 값에 대한 데이터 가져오기
        repository.getSearchResponse(query = query.get().toString())
            .enqueue(object : retrofit2.Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val responseBody = response.body()?.items?: arrayListOf()
                _movieList.postValue(responseBody)
                Log.d("viewModel","getList success : $responseBody")
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("viewModel","getList fail : ${t.message.toString()}")
            }
        })
    }

    override fun getSearchLogList() {
        Log.d("viewModel","getLogList called")
        val responseLogList = repository.getSearchLogResponse()
        _logList.postValue(responseLogList)
        Log.d("MoviewViewModel","getLogList success : $responseLogList")
    }
}
