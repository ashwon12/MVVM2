package com.example.mvvm2.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvm2.data.local.LocalDataSource
import com.example.mvvm2.data.local.LocalDataSourceImpl
import com.example.mvvm2.data.remote.RemoteDatasource
import com.example.mvvm2.data.remote.RemoteDatasourceIpl
import com.example.mvvm2.data.repository.LogRepository
import com.example.mvvm2.data.repository.LogRepositoryImpl
import com.example.mvvm2.data.repository.MovieRepository
import com.example.mvvm2.data.repository.MovieRepositoryIpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class HiltModule{

    @Provides
    fun provideSharedPrefer(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("searchLog", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideLocalDS(sharedPreferences: SharedPreferences) : LocalDataSource{
        return LocalDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun provideLogRepository(localDatasource : LocalDataSource) : LogRepository {
        return LogRepositoryImpl(localDatasource)
    }

    @Provides
    fun provideMovieRepository(remoteDatasource: RemoteDatasource,localDatasource : LocalDataSource) : MovieRepository{
        return MovieRepositoryIpl(remoteDatasource,localDatasource)
    }

    @Provides
    fun provideRemoteDS() : RemoteDatasource{
        return RemoteDatasourceIpl()
    }

}