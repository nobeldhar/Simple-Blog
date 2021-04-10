package com.decimalab.minutehelp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.simple_blog.data.local.converters.BlogConverter
import com.example.simple_blog.data.local.daos.BlogDao
import com.example.simple_blog.data.local.entities.Blog


@Database(entities = [Blog::class], version = 1, exportSchema = false)
@TypeConverters(value = [(BlogConverter::class)])
abstract class BlogDatabase : RoomDatabase() {


    abstract fun blogDao(): BlogDao
}