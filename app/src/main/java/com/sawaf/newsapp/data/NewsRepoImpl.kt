package com.sawaf.newsapp.data

import androidx.lifecycle.LiveData
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.db.ArticleDao
import com.sawaf.newsapp.data.db.ArticleEntity
import com.sawaf.newsapp.data.models.Article
import com.sawaf.newsapp.ui.models.ArticleUi
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao,
    private val retrofitExecutor: RetrofitExecutor
) : NewsRepoInterface {

    override suspend fun getTopHeadlines(country: String): Result<List<Article>> {
        return retrofitExecutor.makeRequest { newsApi.getTopNews(country) }

    }

    override suspend fun saveArticle(articleEntity: ArticleEntity) {
        articleDao.insertArticle(articleEntity)
    }

    override suspend fun removeArticle(articleEntity: ArticleEntity) {
        articleDao.deleteArticle(articleEntity)
    }

    override fun getAllArticles(): LiveData<List<ArticleEntity>> {
        return articleDao.getAllArticles()
    }

}