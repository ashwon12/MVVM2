package com.example.mvvm2.data.repository

import com.example.mvvm2.data.local.LocalDataSource
import com.example.mvvm2.data.local.LocalDataSourceImpl
import javax.inject.Inject


interface LogRepository {
    fun getSearchLogResponse() : ArrayList<String>
}

class LogRepositoryImpl @Inject constructor(private val localDatasource : LocalDataSource) : LogRepository {
    override fun getSearchLogResponse(): ArrayList<String> {
        return localDatasource.getLog()
    }
}