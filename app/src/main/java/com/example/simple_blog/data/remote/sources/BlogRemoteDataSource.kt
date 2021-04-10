package com.example.simple_blog.data.remote.sources

import com.example.simple_blog.data.remote.services.BlogService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRemoteDataSource @Inject constructor(
    private val blogService: BlogService
) : BaseDataSource() {
    suspend fun getBlogPosts() = getResult { blogService.getBlogPostList() }


}