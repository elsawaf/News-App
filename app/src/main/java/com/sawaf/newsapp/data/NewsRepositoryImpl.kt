package com.sawaf.newsapp.data

import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.mappers.ArticleEntityMapper
import com.sawaf.newsapp.domain.common.Result
import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val mapper: ArticleEntityMapper,
    private val retrofitExecutor: RetrofitExecutor
) : NewsRepository {

    override suspend fun getTopHeadlines(country: String): Result<List<Article>> {
//        return retrofitExecutor.makeRequest { newsApi.getTopNews(country) }
        return try {
            val response = newsApi.getTopNews(country)
            if (response.isSuccessful) {
                Result.Success(mapper.toArticleList(response.body()?.data!!))
            } else {
                Result.Error(Exception("Something Wrong Happen"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    }

    override suspend fun bookmark(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getBookmarks(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}