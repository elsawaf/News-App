package com.sawaf.newsapp.domain

import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toEntity
import com.sawaf.newsapp.ui.models.ArticleUi
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface,
) {
    suspend operator fun invoke(articleUi: ArticleUi) {
        if (articleUi.isBookmarked) {
            newsRepoInterface.removeArticle(articleUi.toEntity())
        } else {
            newsRepoInterface.saveArticle(articleUi.toEntity())
        }
    }
}