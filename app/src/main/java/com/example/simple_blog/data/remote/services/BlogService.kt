package com.example.simple_blog.data.remote.services


import com.example.simple_blog.data.remote.response.BlogPostResponse
import retrofit2.Response
import retrofit2.http.*

interface BlogService {

    @GET("simple-blog-api/db")
    suspend fun getBlogPostList(): Response<BlogPostResponse>


}