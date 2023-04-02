package com.sawaf.newsapp.data.mappers

import com.sawaf.newsapp.data.models.ArticleEntity
import com.sawaf.newsapp.data.models.ArticleResponse
import com.sawaf.newsapp.domain.entities.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() {
    fun toArticleResponse(article: Article): ArticleResponse {
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
            articleResponse.urlToImage ?: "",
            false
        )
    }

    fun toArticleEntity(article: Article) = ArticleEntity(
        article.url,
        article.publishedAt,
        article.title,
        article.urlToImage
    )

    fun toArticle(articleEntity: ArticleEntity) = Article(
        articleEntity.title ?: "",
        articleEntity.publishedAt ?: "",
        articleEntity.url,
        articleEntity.urlToImage ?: "",
        true
    )

    fun toArticleList(response: List<ArticleResponse>): List<Article> {
        return response.map {
            toArticle(it)
        }
    }

    fun toArticleBookmarks(list: List<ArticleEntity>): List<Article> {
        return list.map {
            toArticle(it)
        }
    }
}