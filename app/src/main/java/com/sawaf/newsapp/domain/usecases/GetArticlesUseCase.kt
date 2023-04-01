package com.sawaf.newsapp.domain.usecases

import com.sawaf.newsapp.domain.repositories.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(country: String) = newsRepository.getTopHeadlines(country)
}