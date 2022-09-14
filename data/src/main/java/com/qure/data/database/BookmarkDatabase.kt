package com.qure.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qure.domain.model.News

@Database(entities = [News::class], version = 3, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase(){

    abstract fun bookmarkdDao() : BookmarkDao

}