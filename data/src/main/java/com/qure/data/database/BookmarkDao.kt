package com.qure.data.database

import androidx.room.*
import com.qure.domain.model.News

@Dao
interface BookmarkDao {

 @Query("SELECT * FROM bookmark_table")
 fun getAll() : List<News>

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun insert(news: News)

 @Delete
 fun delete(news: News)

 @Query("SELECT count(*) FROM bookmark_table WHERE bookmark_table.title = :title")
 fun check(title: String) : Int


}