package com.example.simple_blog.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simple_blog.data.local.entities.Blog

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBlogPosts(items: List<Blog>)

    @Update
    suspend fun updateBlogPost(item: Blog)

    @Query("SELECT * from blog_table")
    fun getBlogPosts(): LiveData<List<Blog>>

    @Query("SELECT * from blog_table where id = :blogId")
    fun getBlogById(blogId: Int): LiveData<Blog>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBlog(blog: Blog)

}