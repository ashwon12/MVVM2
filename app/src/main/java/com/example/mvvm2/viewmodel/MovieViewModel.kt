package com.example.mvvm2.viewmodel

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.repository.MovieRepositoryIpl
import retrofit2.Call
import retrofit2.Response


interface MovieViewModelIpl {
    val movieList : LiveData<ArrayList<ItemX>>
    val query : ObservableField<String>
    fun getSearchResponseList()
}

class MovieViewModel(private val LogButtonClick : (View) -> Unit) : ViewModel(), MovieViewModelIpl {
    private val repository : MovieRepositoryIpl = MovieRepositoryIpl()

    override val query = ObservableField<String>()
    private val _movieList : MutableLiveData<ArrayList<ItemX>> = MutableLiveData()
    override val movieList: LiveData<ArrayList<ItemX>>
        get() = _movieList

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

    fun logClick(v : View){
        LogButtonClick(v)
    }
}

class MovieViewModelFactory(private val LogButtonClick : (View) -> Unit ) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            MovieViewModel(LogButtonClick) as T
        } else{
            throw IllegalArgumentException()
        }
    }
}
