package com.qure.onboarding.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Saved::class], version = 3, exportSchema = false)
abstract class SavedDatabase : RoomDatabase(){

    abstract fun savedDao() : SavedDao

}