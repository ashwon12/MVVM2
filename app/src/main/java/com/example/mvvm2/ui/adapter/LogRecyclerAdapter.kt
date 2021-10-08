package com.example.mvvm2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.databinding.ItemLogBinding

class LogRecyclerAdapter : RecyclerView.Adapter<LogRecyclerAdapter.Holder>() {
    private var logList: ArrayList<String>? = arrayListOf()

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
        logList?.run {
            holder.bind(this[position])
        }

    }

    override fun getItemCount(): Int {
        return if (logList == null) 0 else logList!!.size
    }

    fun setLogItemList(newItem : ArrayList<String>){
        logList?.run {
            clear()
            addAll(newItem)
        }
        logList?.subList(0,4)
    }
}