package com.example.simple_blog.di.module

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.decimalab.minutehelp.data.local.BlogDatabase
import com.example.simple_blog.data.local.daos.BlogDao
import com.example.simple_blog.data.local.entities.Blog
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): BlogDatabase {
        return Room
                .databaseBuilder(application, BlogDatabase::class.java, "blog_database")
                .build()
    }

    @Provides
    @Singleton
    fun provideBlogDao(@NonNull database: BlogDatabase): BlogDao {
        return database.blogDao()
    }
}