package com.qure.onboarding.ui.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qure.onboarding.AdatperListener.NewsAdapterListener
import com.qure.onboarding.model.News
import com.qure.onboarding.databinding.ItemNewListBinding


class NewsAdapter(
    val context: Context,
    private val listener: NewsAdapterListener
) : ListAdapter<News, NewsAdapter.ViewHolder>(DifferCallback) {

    companion object {
        val DifferCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewListBinding.inflate(
            layoutInflater, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {

        holder.bind(currentList[position])

    }

    inner class ViewHolder(private val binding: ItemNewListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {

            var news = News(
                item.author,
                item.title,
                item.description,
                item.url,
                item.urlToImage,
                item.publishedAt!!,
                item.content
            )

            if (news.author == null || item.author!!.contains("<a hre") || item.author!!.contains("https://")) {
                news = News(
                    "",
                    item.title,
                    item.description,
                    item.url,
                    item.urlToImage,
                    item.publishedAt!!,
                    item.content
                )
            }

            binding.news = news

            itemView.setOnClickListener {
                listener.onItemNews(news)
            }

        }
    }
}


