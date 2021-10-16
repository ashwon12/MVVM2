package com.example.mvvm2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.repository.MovieRepositoryIpl
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val repository: MovieRepositoryIpl = MovieRepositoryIpl()
    val query: MutableLiveData<String> = MutableLiveData()

    private val _emptyText: MutableLiveData<Boolean> = MutableLiveData()
    val emptyText: LiveData<Boolean>
        get() = _emptyText

    private val _movieList: MutableLiveData<ArrayList<ItemX>> = MutableLiveData()
    val movieList: LiveData<ArrayList<ItemX>>
        get() = _movieList

    val showToast: MutableLiveData<String> = MutableLiveData()

    fun search() {
        val query = query.value.also {
            if (it.isNullOrBlank()) {
                showToast.value = "검색어를 입력해주세요!"
                return
            }
        }

        //입력 값에 대한 데이터 가져오기
        query?.let {
            repository.getSearchResponse(query = it)
                .enqueue(object : retrofit2.Callback<SearchResponse> {
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {
                        val resultList = response.body()?.items?: arrayListOf()

                        resultList?.run {
                            _movieList.value = this
                            _emptyText.value = this.size == 0
                        }

                        repository.saveSearchLog(it)
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        showToast.value = "에러입니다~"
                    }
                })
        }
    }
}

