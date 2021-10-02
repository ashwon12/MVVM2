package com.example.mvvm2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.dto.Item
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.retrofit.RetrofitClient
import com.example.mvvm2.data.retrofit.api.SearchAPI
import retrofit2.Call
import retrofit2.Response

interface MovieViewModel {
    val movieList : LiveData<Item>
    fun getList(query : String)
}

class MovieViewModelIpl : ViewModel(), MovieViewModel {

    private val _movieList : MutableLiveData<Item> = MutableLiveData()
    override val movieList: LiveData<Item>
        get() = _movieList

    override fun getList(query : String) {
        val instance = RetrofitClient.getClient()?.create(SearchAPI::class.java)
        val call = instance?.getSearchResponse(query)

        call?.enqueue(object : retrofit2.Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                Log.d("viewModel","getList success")
                val responseBody = response.body()?.item
                _movieList.postValue(responseBody)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("viewModel","getList fail : ${t.message.toString()}")
            }

        })
    }


}