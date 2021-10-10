package com.example.mvvm2.ui.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm2.data.dto.ItemX

object BindingAdapter {

    @BindingAdapter("bind_movieList")
    @JvmStatic
    fun bindMovieList(recyclerView: RecyclerView, movieList: ArrayList<ItemX>?){
        movieList?.run {
            (recyclerView.adapter as MovieRecyclerAdapter).setItemList(this)
        }
    }

    @BindingAdapter("bind_logList")
    @JvmStatic
    fun bindLogList(recyclerView: RecyclerView, logList : ArrayList<String>?){
        logList?.run {
            ((recyclerView.adapter) as LogRecyclerAdapter).setLogItemList(this)
        }
    }

    @BindingAdapter("bind_img")
    @JvmStatic
    fun bindImage(imageView: ImageView, imgUrl : String){
        Glide
            .with(imageView.context)
            .load(imgUrl)
            .into(imageView)
    }

    @BindingAdapter("bind_rating")
    @JvmStatic
    fun bindRating(tvRate : TextView, rate : String){
        tvRate.text = "평점 : $rate"
    }

    @BindingAdapter("bind_title")
    @JvmStatic
    fun bindTitle(tvTitle : TextView, title : String){
        val newTitle  = title.replace("<b>","").replace("</b>","").replace("&amp;","")
        tvTitle.text = newTitle
    }
}