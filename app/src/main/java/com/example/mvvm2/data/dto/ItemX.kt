package com.example.mvvm2.data.dto


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
data class ItemX(
    @SerialName("actor")
    val actor: String,
    @SerialName("director")
    val director: String,
    @SerialName("image")
    val image: String,
    @SerialName("link")
    val link: String,
    @SerialName("pubDate")
    val pubDate: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("title")
    val title: String,
    @SerialName("userRating")
    val userRating: String
): Parcelable