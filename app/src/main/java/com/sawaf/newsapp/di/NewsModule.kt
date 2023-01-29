package com.sawaf.newsapp.di

import com.sawaf.newsapp.data.NewsApi
import com.sawaf.newsapp.data.NewsRepoImpl
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.base.RetrofitExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object NewsModule {

    @Provides
//    @Singleton
    fun provideNewsInterface(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
//    @Singleton
    fun provideNewsRepo(newsApi: NewsApi, retrofitExecutor: RetrofitExecutor): NewsRepoInterface {
        return NewsRepoImpl(newsApi, retrofitExecutor)
    }
}