package com.sawaf.newsapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUi(
    val title: String,
    val url: String,
    val urlToImage: String,
    val description: String,
    val sourceName: String,
    var isBookmarked: Boolean
): Parcelable
