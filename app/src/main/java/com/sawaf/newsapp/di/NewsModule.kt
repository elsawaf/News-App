package com.sawaf.newsapp.di

import com.sawaf.newsapp.data.NewsApi
import com.sawaf.newsapp.data.NewsRepoImpl
import com.sawaf.newsapp.data.NewsRepoInterface
import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.db.ArticleDao
import com.sawaf.newsapp.data.db.ArticleDatabase
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
    fun provideNewsRepo(
        newsApi: NewsApi,
        articleDao: ArticleDao,
        retrofitExecutor: RetrofitExecutor
    ): NewsRepoInterface {
        return NewsRepoImpl(newsApi, articleDao, retrofitExecutor)
    }

    @Provides
    fun provideDao(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }
}