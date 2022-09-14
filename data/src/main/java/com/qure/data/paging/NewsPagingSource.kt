package com.qure.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.qure.data.mapper.Mapper.toDomain
import com.qure.data.network.api.NewsApi

import com.qure.domain.model.News


import kotlinx.coroutines.delay

const val STARTING_KEY = 1
const val DELAY_MILLIS = 1_000L

class NewsPagingSource(
    private val service: NewsApi,
    private val category: String?
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val position = params.key ?: STARTING_KEY
        if (position != STARTING_KEY) delay(DELAY_MILLIS)

        return try {
            val news = service.getNews(category = category, page = position).toDomain()
            LoadResult.Page(
                data = news,
                prevKey = if (position == STARTING_KEY) null else position - 1,
                nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (e: Throwable) {
            return LoadResult.Error(e)
        }

    }
}