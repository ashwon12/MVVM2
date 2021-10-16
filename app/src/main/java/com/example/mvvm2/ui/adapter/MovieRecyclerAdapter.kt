package com.example.mvvm2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ItemMovieBinding

class MovieRecyclerAdapter(private val movieItemClick : (ItemX) -> Unit) : RecyclerView.Adapter<MovieRecyclerAdapter.Holder>() {
    private var movieList: ArrayList<ItemX> = arrayListOf()

    open class Holder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val _binding = binding
        fun bind(movieItem : ItemX){
            _binding.searchItem = movieItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener{movieItemClick(movieList[position])}
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setItemList(newList : ArrayList<ItemX>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}