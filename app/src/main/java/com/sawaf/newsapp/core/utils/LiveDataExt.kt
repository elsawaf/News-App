package com.sawaf.newsapp.core.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sawaf.newsapp.core.Event

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

fun <T> LifecycleOwner.observeEventOnce(liveData: LiveData<Event<T>>, action: (t: T) -> Unit) {
    liveData.observe(this) { it.getContentIfNotHandled()?.also(action) }
}

/**
 * Syntactic sugar for [LiveData.observeNotNull] function where the [Observer] is the last parameter.
 * Hence can be passed outside the function parenthesis.
 */
inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner) { it?.apply(observer) }
}

fun <T> MutableLiveData<Event<T>>.updateValue(data: T, isBackgroundThread: Boolean = false) {
    if (isBackgroundThread)
        postValue(Event(data))
    else
        this.value = Event(data)
}