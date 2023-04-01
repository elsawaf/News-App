package com.sawaf.newsapp.domain.usecases

import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.domain.repositories.NewsRepository
import javax.inject.Inject

class BookmarkArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) = newsRepository.bookmark(article)
}