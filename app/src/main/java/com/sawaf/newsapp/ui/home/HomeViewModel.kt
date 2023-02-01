package com.sawaf.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.Event
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.data.NewsRepoInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepoInterface: NewsRepoInterface
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // event
    val errorMsg = MutableLiveData<Event<String>>()

    init {
        loadData()
    }

    fun loadData() {

        viewModelScope.launch {
            val data = newsRepoInterface.getTopHeadlines("eg")
//            Timber.i("$data")
            if (data is Result.Success) {
                Timber.i(data.data[1].title)
            }
        }


        // Event error example
        /*viewModelScope.launch {
            delay(1000)
            errorMsg.value = Event("No data Error !")
        }*/
    }
}