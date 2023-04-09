package com.sawaf.newsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val description: String,
    val sourceName: String
)
