package com.sawaf.newsapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sawaf.newsapp.databinding.ArticleListItemBinding
import com.sawaf.newsapp.domain.entities.Article
import com.sawaf.newsapp.ui.base.BaseAdapter
import com.sawaf.newsapp.ui.base.BaseDiffCallback

class ArticlesAdapter(
    itemAction: (Article, View) -> Unit
) : BaseAdapter<Article, ArticleListItemBinding>(
    ArticlesDiffCallback(), itemAction
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ArticleListItemBinding {
        return ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: ArticleListItemBinding, position: Int) {
        binding.articleTitle.text = getItem(position).title
    }

}

class ArticlesDiffCallback : BaseDiffCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }
}