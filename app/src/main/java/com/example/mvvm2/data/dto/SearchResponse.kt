package com.example.mvvm2.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("display")
    val display: Int,
    @SerialName("items")
    val items: ArrayList<ItemX>,
    @SerialName("lastBuildDate")
    val lastBuildDate: String,
    @SerialName("start")
    val start: Int,
    @SerialName("total")
    val total: Int
)