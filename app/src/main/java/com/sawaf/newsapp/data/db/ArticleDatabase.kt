package com.sawaf.newsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sawaf.newsapp.data.ARTICLE_DATABASE
import com.sawaf.newsapp.data.models.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao() : ArticleDao

    companion object {
        fun create(context: Context): ArticleDatabase {
            return Room.databaseBuilder(
                context,
                ArticleDatabase::class.java,
                ARTICLE_DATABASE
            ).build()
        }
    }
}