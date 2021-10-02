package com.example.mvvm2.data.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object{
        private var retrofitClient : Retrofit? = null

        fun getClient() : Retrofit? {
            Log.d("RetrofitClient", "getClient() called")

            val baseUrl = "https://openapi.naver.com/v1/search/"
            val client = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)

            //기본 파라메터 인터셉터!!
            val baseParameterInterceptor: Interceptor = (Interceptor { chain ->
                Log.d("RetrofitClient"," intercept() called")

                val builder = chain.request().newBuilder()
                builder.addHeader("X-Naver-Client-Id","KPs1Fz7hGzcZRPFvViN7")
                builder.addHeader("X-Naver-Client-Secret","qm06FtUOJ8")

                chain.proceed(builder.build())
            })

            // 인터셉터 추가
            client.addInterceptor(baseParameterInterceptor)

            if(retrofitClient == null){
                retrofitClient = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
            }
            return retrofitClient
        }
    }
}