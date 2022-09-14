package com.qure.presenation.di

import android.content.Context
import androidx.room.Room
import com.qure.data.database.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BookmarkDatabase::class.java,
        "bookmark_table"
    ).build()

    @Provides
    @Singleton
    fun provideBookmarkDao(database: BookmarkDatabase) = database.bookmarkdDao()
}