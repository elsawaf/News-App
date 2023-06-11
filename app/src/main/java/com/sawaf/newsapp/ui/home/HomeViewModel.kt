package com.sawaf.newsapp.ui.home

import androidx.lifecycle.*
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toEntity
import com.sawaf.newsapp.data.mapper.toUiModel
import com.sawaf.newsapp.ui.base.BaseViewModel
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface
) : BaseViewModel() {

    // merge
    private val _apiList = MutableLiveData<List<ArticleUi>>()

    private val _savedList = newsRepoInterface.getAllUrls()

    val articleList = MediatorLiveData<List<ArticleUi>>()

    init {
        loadData()

        articleList.addSource(_apiList) { articles ->
            mergeArticles(articles, _savedList.value)
        }

        articleList.addSource(_savedList) { urls ->
            mergeArticles(_apiList.value, urls)
        }
    }

    private fun mergeArticles(articles: List<ArticleUi>?, urls: List<String>?) {
        Timber.d("mergeArticle = ${urls?.size}")
        articleList.value = articles?.onEach { item ->
            item.isBookmarked = urls?.find { it == item.url } != null
            Timber.d("find = ${item.isBookmarked}")
        }?.toList()
    }

    fun loadData() {
        viewModelScope.launch {
            handleResult {
                newsRepoInterface.getTopHeadlines("us")
            }?.also { articles ->
                _apiList.value = articles.map { item -> item.toUiModel() }
            }
        }
    }

    fun saveArticle(articleUi: ArticleUi) {
        viewModelScope.launch {
            if (articleUi.isBookmarked) {
                newsRepoInterface.removeArticle(articleUi.toEntity())
            } else {
                newsRepoInterface.saveArticle(articleUi.toEntity())
            }
        }
    }
}