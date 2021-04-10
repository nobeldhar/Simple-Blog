package com.example.simple_blog.data.remote.response


import com.example.simple_blog.data.local.entities.Blog
import com.google.gson.annotations.SerializedName

data class BlogPostResponse(
    @SerializedName("blogs")
    var blogs: List<Blog>
)