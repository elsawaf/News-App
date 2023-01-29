package com.sawaf.newsapp.data

import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.models.Article
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val retrofitExecutor: RetrofitExecutor
) : NewsRepoInterface {

    override suspend fun getTopHeadlines(country: String): Result<List<Article>> {
        return retrofitExecutor.makeRequest { newsApi.getTopNews(country) }

    }
}