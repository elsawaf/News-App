package com.sawaf.newsapp.data.mappers

import com.sawaf.newsapp.data.models.ArticleResponse
import com.sawaf.newsapp.domain.entities.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() {
    fun toArticleEntity(article: Article): ArticleResponse {
        return ArticleResponse(
            title = article.title,
            publishedAt = article.publishedAt,
            url = article.url,
            urlToImage = article.urlToImage
        )
    }

    fun toArticle(articleResponse: ArticleResponse): Article {
        return Article(
            articleResponse.title ?: "",
            articleResponse.publishedAt ?: "",
            articleResponse.url ?: "",
            articleResponse.urlToImage ?: ""
        )
    }

    fun toArticleList(response: List<ArticleResponse>): List<Article> {
        return response.map {
            toArticle(it)
        }
    }
}