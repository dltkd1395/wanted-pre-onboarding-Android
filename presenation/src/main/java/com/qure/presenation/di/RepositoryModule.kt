package com.qure.presenation.di

import com.qure.data.database.BookmarkDao
import com.qure.data.network.api.NewsApi
import com.qure.data.repository.local.BookmarkRepositoryImpl
import com.qure.data.repository.remote.NewsRepositoryImpl
import com.qure.domain.repository.BookmarkRepository
import com.qure.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsApi
        )
    }

    @Provides
    @Singleton
    fun provideBookmarkNewsRepository(
        bookmarkDao: BookmarkDao
    ): BookmarkRepository {
        return BookmarkRepositoryImpl(
            bookmarkDao
        )
    }
}