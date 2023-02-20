package com.example.reactivationnews.di

import android.content.Context
import androidx.room.Room
import com.example.reactivationnews.data.local.NewsDatabase
import com.example.reactivationnews.utils.Constants.ROOM_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        ROOM_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: NewsDatabase) = database.newsDao()

}