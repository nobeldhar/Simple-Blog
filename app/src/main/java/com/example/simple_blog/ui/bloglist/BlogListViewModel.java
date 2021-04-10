package com.example.simple_blog.ui.bloglist;

import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.data.repository.BlogRepository;
import com.example.simple_blog.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BlogListViewModel extends ViewModel {

    private final BlogRepository blogRepository;


    @Inject
    public BlogListViewModel(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public LiveData<Resource<List<Blog>>> getBlogPostList() {
        return blogRepository.getBlogPosts();
    }
}