package com.sawaf.newsapp.ui.dashboard

import androidx.lifecycle.*
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.mapper.toEntity
import com.sawaf.newsapp.data.mapper.toUiModel
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val newsRepo: NewsRepoInterface
) : ViewModel() {

//    private val _articleList = MutableLiveData<List<ArticleUi>>()
    val articleList: LiveData<List<ArticleUi>> =
        newsRepo.getAllArticles().map { it.map { entity -> entity.toUiModel() } }


//    fun loadData() {
//        _articleList.value =
//        newsRepo.getAllArticles().map { it.map { entity -> entity.toUiModel() } }.value
//    }

    fun removeArticle(articleUi: ArticleUi) {
        viewModelScope.launch {
            newsRepo.removeArticle(articleUi.toEntity())
        }
    }
}