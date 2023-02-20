package com.example.reactivationnews.data.local

import androidx.room.*
import com.example.reactivationnews.data.model.ArticlesItem
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(articlesItem: ArticlesItem)

    @Delete
    suspend fun removeSavedNews(articlesItem: ArticlesItem)

    @Query("SELECT * FROM saved_news")
    suspend fun getAllSavedNews(): MutableList<ArticlesItem>
}