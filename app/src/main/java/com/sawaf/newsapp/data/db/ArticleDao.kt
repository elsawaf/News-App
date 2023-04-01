package com.sawaf.newsapp.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sawaf.newsapp.data.ARTICLE_TABLE
import com.sawaf.newsapp.data.models.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articleEntity: ArticleEntity)

    @Delete
    fun deleteArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM $ARTICLE_TABLE")
    fun getAllArticles(): Flow<List<ArticleEntity>>
}