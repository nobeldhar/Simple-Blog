package com.example.simple_blog.di.component

import android.app.Application
import com.decimalab.minutehelp.di.module.NetworkModule
import com.example.simple_blog.di.module.DatabaseModule
import com.example.simple_blog.ui.addNEditBlog.AddNEditFragment
import com.example.simple_blog.ui.blogdetails.BlogDetailsFragment
import com.example.simple_blog.ui.bloglist.BlogListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: BlogListFragment)
    fun inject(fragment : BlogDetailsFragment)
    fun inject(fragment : AddNEditFragment)

}