package com.sawaf.newsapp.data.mappers

import com.sawaf.newsapp.data.models.ArticleEntity
import com.sawaf.newsapp.domain.entities.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() {
    fun toArticleEntity(article: Article): ArticleEntity {
        return ArticleEntity(
            title = article.title,
            publishedAt = article.publishedAt,
            url = article.url,
            urlToImage = article.urlToImage
        )
    }

    fun toArticle(articleEntity: ArticleEntity): Article {
        return Article(
            articleEntity.title ?: "",
            articleEntity.publishedAt ?: "",
            articleEntity.url ?: "",
            articleEntity.urlToImage ?: ""
        )
    }

    fun toArticleList(response: List<ArticleEntity>): List<Article> {
        return response.map {
            toArticle(it)
        }
    }
}