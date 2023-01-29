package com.sawaf.newsapp.data

import com.sawaf.newsapp.data.base.BaseResponse
import com.sawaf.newsapp.data.models.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?apiKey=e7f6d587e5dd41a6bde0791829cbad0f")
    suspend fun getTopNews(@Query("country") country: String) : Response<BaseResponse<List<Article>>>

}