package com.qure.domain.repository

import com.qure.domain.model.News

interface BookmarkRepository {

    suspend fun getBookmark(): List<News>
    suspend fun insertBookmark(news: News)
    suspend fun deleteBookmark(news: News)
    suspend fun checkBookmark(news: News): Int
}