package com.qure.data.repository.local


import com.qure.data.database.BookmarkDao
import com.qure.domain.model.News
import com.qure.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    val bookmarkDao: BookmarkDao
) : BookmarkRepository {
    override suspend fun getBookmark(): List<News> = bookmarkDao.getAll()


    override suspend fun insertBookmark(news: News) = bookmarkDao.insert(news)


    override suspend fun deleteBookmark(news: News) =
        bookmarkDao.delete(news)


    override suspend fun checkBookmark(news: News): Int {
         return bookmarkDao.check(news.title)
    }

}