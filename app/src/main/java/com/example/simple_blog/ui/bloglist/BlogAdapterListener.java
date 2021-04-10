package com.example.simple_blog.ui.bloglist;

import com.example.simple_blog.data.local.entities.Blog;

public interface BlogAdapterListener {
    void onBlogClicked(Blog blog);
}
