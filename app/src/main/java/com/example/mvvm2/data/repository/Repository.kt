package com.example.mvvm2.data.repository

import android.util.Log
import com.example.mvvm2.data.dto.SearchResponse
import com.example.mvvm2.data.local.LocalDataSourceImpl
import com.example.mvvm2.data.remote.RemoteDatasource
import com.example.mvvm2.data.remote.RemoteDatasourceIpl
import com.example.mvvm2.data.remote.api.SearchAPI
import retrofit2.Call

interface Repository {
    fun getSearchResponse(query: String) : Call<SearchResponse>
    fun getSearchLogResponse() : ArrayList<String>
    fun saveSearchLog(keyword : String)
    val localDatasource : LocalDataSourceImpl
    val remoteDatasource : RemoteDatasourceIpl
}

class MovieRepositoryIpl() : Repository {
    override val remoteDatasource: RemoteDatasourceIpl
        get() = RemoteDatasourceIpl()

    override val localDatasource: LocalDataSourceImpl
        get() = LocalDataSourceImpl()

    override fun getSearchResponse(
        query: String
    ): Call<SearchResponse> {
        Log.d("MovieRepositoryIpl", "getSearchResponse called")
        return remoteDatasource.getSearchResponse(query)
    }

    override fun getSearchLogResponse(): ArrayList<String> {
        return localDatasource.getLog()
    }

    override fun saveSearchLog(keyword: String) {
        return localDatasource.saveLog(keyword)
    }
}