package com.app.taazanews.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getNews(@Query("country") country: String,@Query("category") category: String?, @Query("apiKey") apiKey: String): NewsResponse
}