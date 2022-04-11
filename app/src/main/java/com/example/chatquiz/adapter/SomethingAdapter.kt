package com.example.chatquiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatquiz.R
import com.example.chatquiz.databinding.ItemBinding
import com.example.data.models.SomethingDB
import kotlinx.android.synthetic.main.item.view.*

class SomethingAdapter: RecyclerView.Adapter<SomethingAdapter.ViewHolder>() {

    var items: List<SomethingDB> = listOf()

    inner class ViewHolder(
        private val binding: ItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SomethingDB) {
            itemView.apply {
                title_text.text = model.message
                subtitle_text.text = model.fio
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun set(items: List<SomethingDB>) {
        this.items = listOf()
        this.items = items
        notifyDataSetChanged()
    }

}