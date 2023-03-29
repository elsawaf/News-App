package com.sawaf.newsapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sawaf.newsapp.core.Result
import com.sawaf.newsapp.Event
import com.sawaf.newsapp.core.utils.updateValue
import kotlinx.coroutines.delay

open class BaseViewModel : ViewModel() {

    // event
    val errorMsg = MutableLiveData<Event<String?>>()
    val isLoading = MutableLiveData(Event(false))

    suspend fun <T> handleResult(apiAction: suspend () -> Result<T>) : T? {
        isLoading.updateValue(true)
        delay(300)

        var resultData : T? = null
        val response = apiAction()

        when (response) {
            is Result.Error -> {
                errorMsg.updateValue(response.exception.message)
            }
            is Result.Success -> {
                resultData = response.data
            }
        }

        isLoading.updateValue(false)
        return resultData
    }

}