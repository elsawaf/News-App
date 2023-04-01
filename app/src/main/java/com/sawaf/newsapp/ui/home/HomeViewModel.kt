package com.sawaf.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.core.Event
import com.sawaf.newsapp.core.utils.updateValue
import com.sawaf.newsapp.domain.common.Result
import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.domain.usecases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>> = _articleList

    // event
    val errorMsg = MutableLiveData<Event<String?>>()
    val isLoading = MutableLiveData(Event(false))

    init {
        loadData()
    }

    fun loadData() {

        viewModelScope.launch {
            isLoading.updateValue(true)
            delay(300)
            val data = getArticlesUseCase("eg")
//            Timber.i("$data")
            if (data is Result.Success) {
                Timber.i(data.data[1].title)
            }
            when (data) {
                is Result.Success -> {
                    _articleList.value = data.data ?: emptyList()
                }
                is Result.Error -> {
                    errorMsg.updateValue(data.exception.message)
                }
                Result.Loading -> TODO()
            }
            isLoading.updateValue(false)
        }

    }
}