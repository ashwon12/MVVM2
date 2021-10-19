package com.example.mvvm2.data.repository

import android.util.Log
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.local.LocalDataSource
import com.example.mvvm2.data.local.LocalDataSourceImpl
import com.example.mvvm2.data.remote.RemoteDatasource
import com.example.mvvm2.data.remote.RemoteDatasourceIpl
import com.example.mvvm2.data.remote.api.SearchAPI
import retrofit2.Call

interface MovieRepository {
    fun getSearchResponse(query: String) : Call<SearchResponse>
    fun saveSearchLog(keyword : String)
}

class MovieRepositoryIpl(private val remoteDatasource: RemoteDatasource, private val localDatasource : LocalDataSource) : MovieRepository {
    override fun getSearchResponse(
        query: String
    ): Call<SearchResponse> {
        return remoteDatasource.getSearchResponse(query)
    }

    override fun saveSearchLog(keyword: String) {
        return localDatasource.saveLog(keyword)
    }
}