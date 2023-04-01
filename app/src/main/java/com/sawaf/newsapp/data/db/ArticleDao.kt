package com.sawaf.newsapp.data.db

import androidx.room.*
import com.sawaf.newsapp.data.ARTICLE_TABLE
import com.sawaf.newsapp.data.models.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleEntity: ArticleEntity)

    @Delete
    suspend fun deleteArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM $ARTICLE_TABLE")
    fun getAllArticles(): Flow<List<ArticleEntity>>
}