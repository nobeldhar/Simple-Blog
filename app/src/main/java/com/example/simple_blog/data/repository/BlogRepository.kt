package com.example.simple_blog.data.repository


import com.example.simple_blog.data.local.daos.BlogDao
import com.example.simple_blog.data.local.entities.Blog
import com.example.simple_blog.data.remote.sources.BlogRemoteDataSource
import com.example.simple_blog.utils.performGetOperation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepository @Inject constructor(
        private val remoteDataSource: BlogRemoteDataSource,
        private val localDataSource: BlogDao
) {


    fun getBlogById(blogId: Int) = localDataSource.getBlogById(blogId)

    suspend fun updateBlogPost(blog: Blog) = localDataSource.updateBlogPost(blog)


    fun getBlogPosts() = performGetOperation(
        databaseQuery = {localDataSource.getBlogPosts()},
        networkCall = { remoteDataSource.getBlogPosts()},
        saveCallResult = {localDataSource.insertBlogPosts(it.blogs)}
    )

    suspend fun postBlog(blog: Blog) = localDataSource.insertBlog(blog)


}