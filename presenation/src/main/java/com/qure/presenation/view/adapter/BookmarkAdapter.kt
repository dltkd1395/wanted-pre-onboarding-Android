package com.qure.presenation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.qure.domain.model.News
import com.qure.presenation.R
import com.qure.presenation.base.BaseHolder
import com.qure.presenation.databinding.ItemNewListBinding

class BookmarkAdapter(private val itemClickListener: (News) -> Unit) :
    ListAdapter<News, BaseHolder<ItemNewListBinding, News>>(newsDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<ItemNewListBinding, News> {
        return NewsHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_new_list,
                parent,
                false
            ),
            itemClickListener
        )
    }

    inner class NewsHolder(binding: ItemNewListBinding, private val itemClickListener: (News) -> Unit)
        : BaseHolder<ItemNewListBinding, News>(binding) {

        init {
            binding.apply {
                itemView.setOnClickListener {
                    topNews?.run {
                        itemClickListener(this)
                    }
                }
            }
        }

        override fun bind(element: News) {
            super.bind(element)
            binding.topNews = element

        }
    }

    companion object {
        private val newsDiffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: BaseHolder<ItemNewListBinding, News>, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}