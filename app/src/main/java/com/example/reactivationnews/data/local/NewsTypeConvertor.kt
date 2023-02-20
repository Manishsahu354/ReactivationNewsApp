package com.example.reactivationnews.data.local

import androidx.room.TypeConverter
import com.example.reactivationnews.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsTypeConvertor {

    val gson = Gson()

    @TypeConverter
    fun sourceToString(source: Source): String {
        return gson.toJson(source)
    }

    @TypeConverter
    fun stringToSource(data: String): Source {
        val listType = object : TypeToken<Source>() {}.type
        return gson.fromJson(data, listType)
    }
}