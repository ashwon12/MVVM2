package com.example.mvvm2.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm2.data.dto.ItemX


class DetailViewModel : ViewModel() {
    private val _detailItem : MutableLiveData<ItemX> = MutableLiveData()
    val detailItem : LiveData<ItemX>
        get() =_detailItem

    fun updateItem(detail : ItemX){
        _detailItem.value = detail
    }
}