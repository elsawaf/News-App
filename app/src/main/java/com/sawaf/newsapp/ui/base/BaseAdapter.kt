package com.sawaf.newsapp.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.sawaf.newsapp.R

/**
 * Utilize creating and binding data into viewHolder with a default [DiffUtil.ItemCallback]
 */
abstract class BaseAdapter<T, V: ViewBinding>(
    callback: DiffUtil.ItemCallback<T> = BaseDiffCallback(),
    private val itemAction: ((T, View) -> Unit)? = null
) :
    ListAdapter<T, BaseViewHolder<V>>(callback) {

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        (holder as BaseViewHolder<*>).binding.root.setTag(R.string.position, position)
        itemAction?.also { action ->
            holder.binding.root.setOnClickListener {
                action.invoke(getItem(position), it)
            }
        }
        bind(holder.binding, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): V

    protected abstract fun bind(binding: V, position: Int)
}
