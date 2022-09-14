package com.qure.data.mapper


import com.qure.data.network.model.NewsResponse
import com.qure.domain.model.News


object Mapper {

    fun NewsResponse.toDomain(): List<News> {

        val list = mutableListOf<News>()

        this.articles!!.map {

            list.add(
                News(
                    it.title,
                    it.author,
                    it.description,
                    it.url,
                    it.urlToImage,
                    it.publishedAt,
                    it.content
                )
            )
        }

        return list
    }
}