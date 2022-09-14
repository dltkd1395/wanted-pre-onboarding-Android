package com.qure.data.repository.local

import com.qure.domain.model.News

interface BookmarkRepository {

    suspend fun getBookmark() : List<News>?
    suspend fun insertBookmark(news: News) : News
    suspend fun deleteBookmark(news: News) : News
    suspend fun checkBookmark(news: News) : Int

}