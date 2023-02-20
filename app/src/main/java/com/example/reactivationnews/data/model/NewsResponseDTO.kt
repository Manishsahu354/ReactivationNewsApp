package com.example.reactivationnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reactivationnews.utils.Constants
import com.google.gson.annotations.SerializedName

data class NewsResponseDTO(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String? = null
)

data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Any? = null
)

@Entity(tableName = Constants.SAVED_NEWS_TABLE_NAME)
data class ArticlesItem(

	@PrimaryKey(autoGenerate = true)
	var id: Int,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
