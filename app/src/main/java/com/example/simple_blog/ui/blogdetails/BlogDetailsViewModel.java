package com.example.simple_blog.ui.blogdetails;

import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.data.repository.BlogRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BlogDetailsViewModel extends ViewModel {

    private BlogRepository blogRepository;

    @Inject
    public BlogDetailsViewModel(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public LiveData<Blog> getBlogById(Integer mBlogId) {
        return blogRepository.getBlogById(mBlogId);
    }
}
