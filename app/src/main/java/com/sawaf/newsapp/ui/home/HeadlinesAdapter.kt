package com.sawaf.newsapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sawaf.newsapp.databinding.HeadlinesListItemBinding
import com.sawaf.newsapp.ui.base.BaseAdapter
import com.sawaf.newsapp.ui.base.BaseDiffCallback
import com.sawaf.newsapp.ui.models.ArticleUi

class HeadlinesAdapter(
    itemAction: (ArticleUi, View) -> Unit,
    private val saveAction: (ArticleUi) -> Unit
): BaseAdapter<ArticleUi, HeadlinesListItemBinding>(
    HeadlinesDiffCallback(),
    itemAction = itemAction
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): HeadlinesListItemBinding {
        return HeadlinesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: HeadlinesListItemBinding, position: Int) {
        val item = getItem(position)
        binding.headlineTitle.text = item.title
        binding.sourceName.text = item.sourceName
        val iconRes = if (item.isBookmarked)
            android.R.drawable.btn_star_big_on
        else
            android.R.drawable.btn_star_big_off

        binding.saveBtn.setImageResource(iconRes)
        binding.saveBtn.setOnClickListener {
            saveAction.invoke(item)
            notifyItemChanged(position)
        }
    }
}

class HeadlinesDiffCallback: BaseDiffCallback<ArticleUi>() {
    override fun areItemsTheSame(oldItem: ArticleUi, newItem: ArticleUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleUi, newItem: ArticleUi): Boolean {
        return oldItem.url == newItem.url && oldItem.isBookmarked == newItem.isBookmarked
    }
}