package com.example.simple_blog.data.local.converters

import androidx.room.TypeConverter
import com.example.simple_blog.data.remote.response.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BlogConverter {

    @TypeConverter
    fun fromStringToAuthor(value: String): Author? {
        val listType = object : TypeToken<Author?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAuthorToString(author: Author?): String {
        val gson = Gson()
        return gson.toJson(author)
    }

    @TypeConverter
    fun fromStringToCategories(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategoriesToString(categories: List<String>): String {
        val gson = Gson()
        return gson.toJson(categories)
    }

}