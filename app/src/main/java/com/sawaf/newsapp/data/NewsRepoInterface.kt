package com.sawaf.newsapp.data

import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.models.Article
import com.sawaf.newsapp.ui.models.ArticleUi

interface NewsRepoInterface {

    suspend fun getTopHeadlines(country: String): Result<List<Article>>
}