package com.example.mvvm2.viewmodel

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm2.App
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.repository.MovieRepositoryIpl
import retrofit2.Call
import retrofit2.Response

interface MovieViewModelIpl {
    val movieList : LiveData<ArrayList<ItemX>>
    val query : ObservableField<String>
    val emptyText : LiveData<Boolean>
    fun getSearchResponseList()
    fun logClick(v : View)
}

class MovieViewModel(private val LogButtonClick : (View) -> Unit) : ViewModel(), MovieViewModelIpl {
    private val repository : MovieRepositoryIpl = MovieRepositoryIpl()
    override val query = ObservableField<String>()

    private val _emptyText : MutableLiveData<Boolean> = MutableLiveData()
    override val emptyText:LiveData<Boolean>
        get()= _emptyText

    private val _movieList : MutableLiveData<ArrayList<ItemX>> = MutableLiveData()
    override val movieList: LiveData<ArrayList<ItemX>>
        get() = _movieList

    override fun getSearchResponseList() {
        val query = query.get().toString()
        if(query !="null" && (query.isNotBlank())){
            //검색 기록 저장하기
            repository.saveSearchLog(query)

            //입력 값에 대한 데이터 가져오기
            repository.getSearchResponse(query = query)
                .enqueue(object : retrofit2.Callback<SearchResponse>{
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {
                        val responseBody = response.body()?.items
                        _movieList.value = responseBody
                        _emptyText.value = movieList.value?.size == 0
                        Log.d("viewModel","${movieList.value?.size}")
                        Log.d("viewModel","getList success : $responseBody")
                    }
                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        Log.d("viewModel","getList fail : ${t.message.toString()}")

                    }
                })
        }else{ // 검색어를 입력하지 않았을 경우
            Toast.makeText(App.instance,"검색어를 입력해주세요!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun logClick(v : View){
        LogButtonClick(v)
    }
}

