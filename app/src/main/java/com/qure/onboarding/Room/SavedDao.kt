package com.qure.onboarding.Room

import androidx.room.*


@Dao
interface SavedDao {

    @Query("SELECT * FROM saved_table")
    fun getAll() : List<Saved>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(saved: Saved)

    @Delete
    fun delete(saved: Saved)

    @Query("SELECT count(*) FROM saved_table WHERE (saved_table.title = :title) AND (saved_table.author=:author)")
    fun check(title: String, author: String) : Int


}