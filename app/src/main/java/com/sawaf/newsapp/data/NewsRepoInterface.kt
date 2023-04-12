package com.sawaf.newsapp.data

import androidx.lifecycle.LiveData
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.db.ArticleEntity
import com.sawaf.newsapp.data.models.Article
import com.sawaf.newsapp.ui.models.ArticleUi

interface NewsRepoInterface {

    suspend fun getTopHeadlines(country: String): Result<List<Article>>

    suspend fun saveArticle(articleEntity: ArticleEntity)

    suspend fun removeArticle(articleEntity: ArticleEntity)

    fun getAllArticles(): LiveData<List<ArticleEntity>>

    fun getAllUrls(): LiveData<List<String>>
}