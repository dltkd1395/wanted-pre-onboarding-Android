package com.qure.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.qure.data.paging.NewsPagingSource
import com.qure.data.network.api.NewsApi


import com.qure.domain.model.News
import com.qure.domain.utils.RemoteErrorEmitter
import com.qure.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {


    override suspend fun getNews(
        remoteErrorEmitter: RemoteErrorEmitter,
        category: String?
    ): Flow<PagingData<News>>? {
        return Pager(PagingConfig(30)) {
            NewsPagingSource(newsApi, category)
        }.flow
    }



}