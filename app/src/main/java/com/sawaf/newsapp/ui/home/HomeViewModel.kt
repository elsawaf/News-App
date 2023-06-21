package com.sawaf.newsapp.ui.home

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.core.utils.readString
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toEntity
import com.sawaf.newsapp.data.mapper.toUiModel
import com.sawaf.newsapp.ui.base.BaseViewModel
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface,
    @ApplicationContext val context: Context
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
        val newArticles = articles?.toMutableList()
        articleList.value = newArticles?.onEachIndexed { index, item ->
            urls?.contains(item.url)?.also {
                if (item.isBookmarked != it) {
                    newArticles[index] = item.copy(isBookmarked = it)
                }
            }
        }
    }

    fun loadData() {
        viewModelScope.launch {
            val countryCode = context.readString("apiKey", "us").first()
            handleResult {
                newsRepoInterface.getTopHeadlines(countryCode)
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