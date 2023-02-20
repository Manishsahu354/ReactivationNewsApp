package com.example.reactivationnews.data.network

import com.example.reactivationnews.data.model.NewsResponseDTO
import com.example.reactivationnews.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponseDTO

}