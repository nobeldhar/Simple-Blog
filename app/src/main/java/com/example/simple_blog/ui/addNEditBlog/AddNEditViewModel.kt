package com.example.simple_blog.ui.addNEditBlog;

import androidx.lifecycle.*
import com.example.simple_blog.R
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

    val addEditEvent = MutableLiveData<AddEditEvent>()

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
        } else if (isValid) {
            mBlog?.let {
                viewModelScope.launch {
                    blogRepository.postBlog(it)
                }
                submitResult.value = "Post Successful"
            }
        } else
            submitResult.value = "Enter all information"
    }

    fun decideAction(mBlogId: Int?) {
        mBlogId?.let {
            addEditEvent.value = AddEditEvent.EDIT_BLOG_ACTION
            isUpdate = true
        } ?: kotlin.run {
            addEditEvent.value = AddEditEvent.ADD_BLOG_ACTION
            isUpdate = false
        }
    }

    fun onCheckBoxChanged(id: Int, isChecked: Boolean) {
        if (isChecked) {
            when (id) {
                R.id.cb_business -> if (mBlog?.categories?.contains(AddNEditFragment.BUSINESS) != true)
                    mBlog?.categories?.add(AddNEditFragment.BUSINESS)
                R.id.cb_entertainment -> if (mBlog?.categories?.contains(AddNEditFragment.ENTERTAINMENT) != true)
                    mBlog?.categories?.add(AddNEditFragment.ENTERTAINMENT)
                R.id.cb_lifestyle -> if (mBlog?.categories?.contains(AddNEditFragment.LIFESTYLE) != true)
                    mBlog?.categories?.add(AddNEditFragment.LIFESTYLE)
                R.id.cb_productivity -> if (mBlog?.categories?.contains(AddNEditFragment.PRODUCTIVITY) != true)
                    mBlog?.categories?.add(AddNEditFragment.PRODUCTIVITY)
            }
        } else {
            when (id) {
                R.id.cb_business -> mBlog?.categories?.remove(AddNEditFragment.BUSINESS)
                R.id.cb_entertainment -> mBlog?.categories?.remove(AddNEditFragment.ENTERTAINMENT)
                R.id.cb_lifestyle -> mBlog?.categories?.remove(AddNEditFragment.LIFESTYLE)
                R.id.cb_productivity -> mBlog?.categories?.remove(AddNEditFragment.PRODUCTIVITY)
            }
        }
    }

    private val isValid: Boolean
        get() = if (mBlog?.title?.isEmpty() == true || mBlog?.description?.isEmpty() == true) false
        else mBlog?.categories?.size != 0

}

enum class AddEditEvent {
    EDIT_BLOG_ACTION, ADD_BLOG_ACTION
}
