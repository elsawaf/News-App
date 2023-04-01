package com.sawaf.newsapp.data.models

data class ArticleEntity(
    val content: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val author: String? = null
)