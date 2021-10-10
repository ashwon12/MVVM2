package com.example.mvvm2.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieViewModelFactory(private val LogButtonClick : (View) -> Unit ) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            MovieViewModel(LogButtonClick) as T
        } else{
            throw IllegalArgumentException()
        }
    }
}