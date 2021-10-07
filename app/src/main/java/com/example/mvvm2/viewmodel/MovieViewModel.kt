package com.example.mvvm2.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.retrofit.RetrofitClient

import com.example.mvvm2.data.retrofit.api.SearchAPI
import retrofit2.Call
import retrofit2.Response


interface MovieViewModelIpl {
    val movieList : LiveData<ArrayList<ItemX>>
    fun getList()
}

class MovieViewModel() : ViewModel(), MovieViewModelIpl {

    val query = ObservableField<String>()
    private val _movieList : MutableLiveData<ArrayList<ItemX>> = MutableLiveData()
    override val movieList: LiveData<ArrayList<ItemX>>
        get() = _movieList

    override fun getList() {
        val instance = RetrofitClient.getClient().create(SearchAPI::class.java)
        val call = instance.getSearchResponse(query.get().toString())

        call.enqueue(object : retrofit2.Callback<SearchResponse>{
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

}
