package com.qure.data.repository.remote

import androidx.paging.PagingData
import com.qure.domain.model.News
import com.qure.domain.utils.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(remoteErrorEmitter: RemoteErrorEmitter, category : String?) : Flow<PagingData<News>>?

}