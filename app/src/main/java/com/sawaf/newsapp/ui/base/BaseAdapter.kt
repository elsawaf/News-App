package com.sawaf.newsapp.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T: Any, V: ViewBinding>(
    callback: BaseDiffCallback<T> = BaseDiffCallback(),
    private val itemAction: ((T, View) -> Unit)? = null
) : ListAdapter<T, BaseViewHolder<V>>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = BaseViewHolder(createBinding(parent, viewType))


    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        itemAction?.also { action ->
            holder.binding.root.setOnClickListener {
                action(getItem(position), it)
            }
        }
        bind(holder.binding, position)
    }

    abstract fun createBinding(parent: ViewGroup, viewType: Int): V

    abstract fun bind(binding: V, position: Int)
}