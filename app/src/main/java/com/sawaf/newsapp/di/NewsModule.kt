package com.sawaf.newsapp.di

import com.sawaf.newsapp.data.api.NewsApi
import com.sawaf.newsapp.data.base.RetrofitExecutor
import com.sawaf.newsapp.data.db.ArticleDao
import com.sawaf.newsapp.data.db.ArticleDatabase
import com.sawaf.newsapp.data.mappers.ArticleEntityMapper
import com.sawaf.newsapp.data.repos.NewsRepositoryImpl
import com.sawaf.newsapp.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

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
        mapper: ArticleEntityMapper,
        retrofitExecutor: RetrofitExecutor
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, articleDao, mapper, retrofitExecutor)
    }

    @Provides
    fun provideDao(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }
}