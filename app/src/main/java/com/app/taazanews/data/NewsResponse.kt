package com.app.taazanews.data

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)