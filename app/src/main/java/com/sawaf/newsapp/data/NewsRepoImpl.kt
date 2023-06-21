package com.sawaf.newsapp.data

import androidx.lifecycle.LiveData
import com.sawaf.newsapp.core.PreferenceManager
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.db.ArticleDao
import com.sawaf.newsapp.data.db.ArticleEntity
import com.sawaf.newsapp.data.models.Article
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao,
    private val retrofitExecutor: RetrofitExecutor,
    private val preferenceManager: PreferenceManager
) : NewsRepoInterface {

    override suspend fun getTopHeadlines(): Result<List<Article>> {
        val countryCode = preferenceManager.getCountryCode()
        return retrofitExecutor.makeRequest { newsApi.getTopNews(countryCode) }

    }

    override suspend fun saveCountryPref(code: String) {
        preferenceManager.saveCountryCode(code)
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

    override fun getAllUrls(): LiveData<List<String>> {
        return articleDao.getArticlesUrl()
    }

}