package com.example.cleanarchitecture.presenter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.main.dto.Pokemon
import com.example.cleanarchitecture.databinding.ItemLayoutBinding

class MainAdapter : PagingDataAdapter<Pokemon, MainAdapter.MainViewHolder>(diffUtil) {

    var onItemClick: ((Pokemon) -> Unit)? = null

    inner class MainViewHolder constructor(
        private val binding: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick?.invoke(it)
                }
            }
        }
        fun bind(item: Pokemon) {
            binding.pokemon = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}