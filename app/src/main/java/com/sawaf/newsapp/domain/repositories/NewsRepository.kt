package com.sawaf.newsapp.domain.repositories

import com.sawaf.newsapp.domain.common.Result
import com.sawaf.newsapp.domain.entities.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopHeadlines(country: String): Result<List<Article>>

    suspend fun bookmark(article: Article)

    suspend fun remove(article: Article)

    fun getBookmarks(): Flow<List<Article>>
}