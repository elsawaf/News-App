package com.sawaf.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawaf.newsapp.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // event
    val errorMsg = MutableLiveData<Event<String>>()

    fun loadData() {
        // Event error example

        /*viewModelScope.launch {
            delay(1000)
            errorMsg.value = Event("No data Error !")
        }*/
    }
}