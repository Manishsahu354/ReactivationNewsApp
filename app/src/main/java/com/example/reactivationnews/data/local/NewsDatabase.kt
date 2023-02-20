package com.example.reactivationnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reactivationnews.data.model.ArticlesItem

@Database(
    entities = [ArticlesItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun newsDao(): NewsDao
}