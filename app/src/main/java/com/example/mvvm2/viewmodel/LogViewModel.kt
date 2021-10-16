package com.example.mvvm2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.repository.MovieRepositoryIpl

class LogViewModel : ViewModel() {
    private val repository : MovieRepositoryIpl = MovieRepositoryIpl()
    private val _logList : MutableLiveData<ArrayList<String>> = MutableLiveData()
    val logList: LiveData<ArrayList<String>>
        get() = _logList

    fun getSearchLogList() {
        val responseLogList = repository.getSearchLogResponse()
        _logList.postValue(responseLogList)
        Log.d("LogViewModel","getLogList success : $responseLogList")
    }
}