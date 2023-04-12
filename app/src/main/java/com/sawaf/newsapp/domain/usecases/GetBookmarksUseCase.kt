package com.sawaf.newsapp.domain.usecases

import com.sawaf.newsapp.domain.repositories.NewsRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getBookmarks()
}