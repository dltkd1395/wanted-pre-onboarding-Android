package com.qure.domain.usecase

import com.qure.domain.repository.NewsRepository
import com.qure.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GetNewsRepoUseCase @Inject constructor(
    private val newsRepository : NewsRepository
) {
    suspend fun getNews(remoteErrorEmitter : RemoteErrorEmitter, category : String?)
    = newsRepository.getNews(remoteErrorEmitter, category)

}