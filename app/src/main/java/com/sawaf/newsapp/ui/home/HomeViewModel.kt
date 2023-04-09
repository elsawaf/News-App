package com.sawaf.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.Event
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.core.utils.updateValue
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toUiModel
import com.sawaf.newsapp.data.models.Article
import com.sawaf.newsapp.ui.base.BaseViewModel
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface
) : BaseViewModel() {

    private val _articleList = MutableLiveData<List<ArticleUi>>()
    val articleList: LiveData<List<ArticleUi>> = _articleList

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            handleResult {
                newsRepoInterface.getTopHeadlines("us")
            }?.also {
                _articleList.value = it.map { item -> item.toUiModel() }
            }
        }
    }
}