package com.example.simple_blog.ui.addNEditBlog;

import androidx.lifecycle.*
import com.example.simple_blog.data.local.entities.Blog
import com.example.simple_blog.data.repository.BlogRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNEditViewModel
@Inject constructor(private val blogRepository: BlogRepository)
    : ViewModel() {

    var mBlog: Blog? = null
    var isUpdate = false

    val submitResult = MutableLiveData<String>()

    fun getBlogById(mBlogId: Int?): LiveData<Blog> {
        return blogRepository.getBlogById(mBlogId!!)
    }

    fun onSubmitClicked() {
        if (isUpdate && isValid) {
            mBlog?.let {
                viewModelScope.launch {
                    blogRepository.updateBlogPost(it)
                }
                submitResult.value = "Update Successful"
            }
        }else if (isValid){
            mBlog?.let {
                viewModelScope.launch {
                    blogRepository.postBlog(it)
                }
                submitResult.value = "Post Successful"
            }
        }else
            submitResult.value = "Enter all information"
    }

    private val isValid: Boolean
        get() = if (mBlog?.title?.isEmpty() == true || mBlog?.description?.isEmpty() == true) false
        else mBlog?.categories?.size != 0

}
