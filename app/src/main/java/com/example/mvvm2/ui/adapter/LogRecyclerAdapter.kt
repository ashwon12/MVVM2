package com.example.mvvm2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.data.dto.ItemX
import com.example.mvvm2.databinding.ItemLogBinding

class LogRecyclerAdapter(private val logItemClick : (String) -> Unit) : RecyclerView.Adapter<LogRecyclerAdapter.Holder>() {
    private var logList = mutableListOf<String>()

    open class Holder(binding : ItemLogBinding) :RecyclerView.ViewHolder(binding.root) {
        private val _binding = binding
        fun bind(logItem : String){
            _binding.searchLog = logItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(logList[position])
        holder.itemView.setOnClickListener{logItemClick(logList[position])}
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    fun setLogItemList(newItem : ArrayList<String>){
        val cutItem = newItem.subList(0,5)
        logList.run {
            clear()
            addAll(cutItem)
        }
    }
}