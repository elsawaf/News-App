package com.sawaf.newsapp.data.models

data class Article(
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val author: String? = null
)