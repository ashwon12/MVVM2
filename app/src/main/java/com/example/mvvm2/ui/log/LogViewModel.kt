package com.example.mvvm2.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LogViewModel @Inject constructor(private val logRepository : LogRepository): ViewModel() {
    private val _logList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val logList: LiveData<ArrayList<String>>
        get() = _logList

    fun logList() {
        _logList.value = logRepository.getSearchLogResponse()
    }
}