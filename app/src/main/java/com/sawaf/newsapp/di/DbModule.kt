package com.sawaf.newsapp.di

import android.content.Context
import com.sawaf.newsapp.data.db.ArticleDao
import com.sawaf.newsapp.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.create(context)
    }

    @Provides
    fun provideDao(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }
}