package com.sawaf.newsapp.data

import androidx.lifecycle.LiveData
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.db.ArticleEntity
import com.sawaf.newsapp.data.models.Article

interface NewsRepoInterface {

    suspend fun getTopHeadlines(): Result<List<Article>>

    suspend fun saveCountryPref(code: String)

    suspend fun saveArticle(articleEntity: ArticleEntity)

    suspend fun removeArticle(articleEntity: ArticleEntity)

    fun getAllArticles(): LiveData<List<ArticleEntity>>

    fun getAllUrls(): LiveData<List<String>>
}