package com.example.mvvm2.data.remote

import android.util.Log
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.remote.api.RetrofitClient
import com.example.mvvm2.data.remote.api.SearchAPI
import retrofit2.Call

interface RemoteDatasource{
    fun getSearchResponse(query: String) : Call<SearchResponse>
}

class RemoteDatasourceIpl() : RemoteDatasource{
    private val searchAPI = RetrofitClient.getClient().create(SearchAPI::class.java)

    override fun getSearchResponse(query: String): Call<SearchResponse> {
        Log.d("RemoteDatasourceIpl","getSearchResponse called")
        return searchAPI.getSearchResponse(query)
    }
}