package com.sawaf.newsapp.data.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val status: String,
    @SerializedName(value = "articles", alternate = ["sources"])
    val data: T,
    val code: String
)
