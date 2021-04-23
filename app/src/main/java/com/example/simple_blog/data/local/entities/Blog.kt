package com.example.simple_blog.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simple_blog.data.remote.response.Author
import com.google.gson.annotations.SerializedName


@Entity(tableName = "blog_table")
data class Blog(
        @SerializedName("author")
        var author: Author?,
        @SerializedName("categories")
        var categories: ArrayList<String>? ,
        @SerializedName("cover_photo")
        var coverPhoto: String?,
        @SerializedName("description")
        var description: String?,
        @SerializedName("title")
        var title: String?,
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        var id: Int? = 0,
)