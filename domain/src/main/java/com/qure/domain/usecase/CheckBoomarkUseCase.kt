package com.qure.domain.usecase

import com.qure.domain.model.News
import com.qure.domain.repository.BookmarkRepository
import javax.inject.Inject

class CheckBoomarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {

    suspend fun getCountBookmark(news: News) : Int = bookmarkRepository.checkBookmark(news)
}