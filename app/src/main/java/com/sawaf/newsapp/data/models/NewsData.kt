package com.sawaf.newsapp.data.models

data class NewsData(
    val articles: List<ArticleResponse>,
    val status: String
)