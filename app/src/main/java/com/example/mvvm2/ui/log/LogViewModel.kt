package com.example.mvvm2.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.repository.MovieRepositoryIpl

class LogViewModel : ViewModel() {
    private val repository: MovieRepositoryIpl = MovieRepositoryIpl()

    private val _logList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val logList: LiveData<ArrayList<String>>
        get() = _logList

    fun logList() {
        _logList.value = repository.getSearchLogResponse()
    }
}