package com.sawaf.newsapp.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.domain.usecases.BookmarkArticleUseCase
import com.sawaf.newsapp.domain.usecases.GetBookmarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val bookmarkArticleUseCase: BookmarkArticleUseCase

) : ViewModel() {

    private val _bookmarksList = MutableStateFlow<List<Article>>(emptyList())
    val bookmarksList: StateFlow<List<Article>> = _bookmarksList

    init {
        loadBookmarks()
    }

    private fun loadBookmarks(){
        viewModelScope.launch {
            getBookmarksUseCase().collect {
                _bookmarksList.value = it
            }
        }
    }

    fun bookmarkArticle(article: Article) {
        viewModelScope.launch {
            bookmarkArticleUseCase.invoke(article)
        }
    }
}