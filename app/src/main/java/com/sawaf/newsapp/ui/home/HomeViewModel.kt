package com.sawaf.newsapp.ui.home

import androidx.lifecycle.*
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toEntity
import com.sawaf.newsapp.data.mapper.toUiModel
import com.sawaf.newsapp.ui.base.BaseViewModel
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface
) : BaseViewModel() {

    private val _apiList = MutableLiveData<List<ArticleUi>>()

    private val _savedList: LiveData<List<ArticleUi>> =
        newsRepoInterface.getAllArticles().map { it.map { entity -> entity.toUiModel() } }

    val articleList = MediatorLiveData<List<ArticleUi>>().apply {
        addSource(_apiList) { list ->
            value = mergeBookmarks(list, _savedList.value)
        }
        addSource(_savedList) {
            value = mergeBookmarks(_apiList.value, it)
        }
    }


    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            handleResult {
                newsRepoInterface.getTopHeadlines("us")
            }?.also {
                _apiList.value = it.map { item -> item.toUiModel() }
            }
        }
    }

    fun saveArticle(articleUi: ArticleUi) {
        viewModelScope.launch {
            newsRepoInterface.saveArticle(articleUi.toEntity())
        }
    }

    private fun mergeBookmarks(apiList: List<ArticleUi>?, savedList: List<ArticleUi>?) =
        apiList?.onEach { item ->
            item.isBookmarked = savedList?.find {
                it.url == item.url
            }?.isBookmarked ?: false
        }
}