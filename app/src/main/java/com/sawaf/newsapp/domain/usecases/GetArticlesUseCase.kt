package com.sawaf.newsapp.domain.usecases

import com.sawaf.newsapp.domain.common.Result
import com.sawaf.newsapp.domain.common.succeeded
import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(country: String): Result<List<Article>> {
        val result = newsRepository.getTopHeadlines(country)
        return if (result.succeeded) {
            val bookmarks = newsRepository.getBookmarks().firstOrNull()
                (result as Result.Success).data.forEach { item ->
                    item.isBookmarked = bookmarks?.find { it.url == item.url }?.isBookmarked ?: false
                }

            result
        } else {
            result
        }
    }
}