package com.example.mvvm2.data.retrofit.api

import com.example.mvvm2.data.dto.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("movie.json")
    fun getSearchResponse(
        @Query("query") query : String
    ):Call<SearchResponse>
}