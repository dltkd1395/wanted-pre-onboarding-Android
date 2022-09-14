package com.qure.presenation.di

import com.qure.domain.repository.BookmarkRepository
import com.qure.domain.repository.NewsRepository
import com.qure.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNewsRepoUseCase(repository : NewsRepository) = GetNewsRepoUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBookmarkUseCase(repository : BookmarkRepository) = GetBookmarkUseCase(repository)

//    @Provides
//    @Singleton
//    fun provideInsertBookmarkUseCase(repository : BookmarkRepository) = InsertBookmarkUseCase(repository)
//
//    @Provides
//    @Singleton
//    fun provideDeleteBookmarkUseCase(repository : BookmarkRepository) = DeleteBookmarkUseCase(repository)
//
//    @Provides
//    @Singleton
//    fun provideCheckBookmarkUseCase(repository : BookmarkRepository) = CheckBoomarkUseCase(repository)
}