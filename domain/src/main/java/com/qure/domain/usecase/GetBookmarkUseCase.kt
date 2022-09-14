package com.qure.domain.usecase

import com.qure.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {

    suspend operator fun invoke() = bookmarkRepository.getBookmark()

}