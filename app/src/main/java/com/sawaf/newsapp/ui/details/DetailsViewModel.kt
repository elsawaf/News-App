package com.sawaf.newsapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.domain.SaveArticleUseCase
import com.sawaf.newsapp.ui.models.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val saveArticleUseCase: SaveArticleUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val articleItem = MutableLiveData<ArticleUi>(savedStateHandle["article"])

    fun saveArticle(articleUi: ArticleUi) {
        viewModelScope.launch {
            saveArticleUseCase(articleUi)
            articleItem.value = articleUi.copy(isBookmarked = !articleUi.isBookmarked)
        }
    }
}