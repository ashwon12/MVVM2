package com.example.mvvm2.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ItemLogBinding

class LogRecyclerAdapter(private val logItemClick: (String) -> Unit) :
    RecyclerView.Adapter<LogRecyclerAdapter.Holder>() {
    private var logList: ArrayList<String> = arrayListOf()

    inner class Holder(private val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(logItem: String) {
            binding.searchLog = logItem
            binding.root.setOnClickListener {logItemClick(logItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(logList[position])
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    fun setLogItemList(newItem: ArrayList<String>) {
        val lastIndex = if (newItem.size < 5) newItem.size else 5
        val cutItem = newItem.subList(0, lastIndex)
        logList.run {
            clear()
            addAll(cutItem)
        }
        notifyDataSetChanged()
    }
}