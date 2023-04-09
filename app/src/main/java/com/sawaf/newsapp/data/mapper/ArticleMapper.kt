package com.sawaf.newsapp.data.mapper

import com.sawaf.newsapp.data.db.ArticleEntity
import com.sawaf.newsapp.data.models.Article
import com.sawaf.newsapp.ui.models.ArticleUi


fun Article.toUiModel() : ArticleUi {
    return ArticleUi(
        title = title,
        description = description ?: "",
        url = url,
        urlToImage = urlToImage ?: "",
        sourceName = source.name
    )
}

fun ArticleUi.toEntity() = ArticleEntity(
    title,
    url,
    urlToImage,
    description,
    sourceName
)

fun ArticleEntity.toUiModel() = ArticleUi(
    title,
    url,
    urlToImage,
    description,
    sourceName
)