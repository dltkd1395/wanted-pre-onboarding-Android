package com.qure.presenation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.qure.domain.model.News
import com.qure.domain.model.NewsResponse

import com.qure.presenation.base.BaseHolder
import com.qure.presenation.base.BasePagingAdapter
import com.qure.presenation.databinding.ItemNewListBinding

class NewsPagingAdapter(private val itemClickListener: (News) -> Unit) : BasePagingAdapter<News>(itemCallback) {

    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<out ViewDataBinding, News> {
        return NewsHolder(
            ItemNewListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
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
}