package com.example.mvvm2.data.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    fun getClient() : Retrofit {
        Log.d("RetrofitClient", "getClient() called")

        val baseUrl = "https://openapi.naver.com/v1/search/"

        val baseInterceptor : Interceptor = (Interceptor { chain ->
            Log.d("RetrofitClient"," intercept() called")
            val builder = chain.request().newBuilder()
                .addHeader("X-Naver-Client-Id","KPs1Fz7hGzcZRPFvViN7")
                .addHeader("X-Naver-Client-Secret","qm06FtUOJ8")
                .build()

            chain.proceed(builder)
        })

        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(baseInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}