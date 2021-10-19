package com.example.mvvm2.data.repository

import com.example.mvvm2.data.local.LocalDataSourceImpl


interface LogRepository {
    fun getSearchLogResponse() : ArrayList<String>

}

class LogRepositoryImpl(private val localDatasource : LocalDataSourceImpl) : LogRepository {
    override fun getSearchLogResponse(): ArrayList<String> {
        return localDatasource.getLog()
    }
}