package com.sawaf.newsapp.data.base

data class BaseResponse<T>(
    val status: String,
    val data: T,
    val code: String
)
