package com.sawaf.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sawaf.newsapp.data.ARTICLE_TABLE

@Entity(tableName = ARTICLE_TABLE)
data class ArticleEntity(
    @PrimaryKey
    val url: String,
    val publishedAt: String? = null,
    val title: String? = null,
    val urlToImage: String? = null,
)
