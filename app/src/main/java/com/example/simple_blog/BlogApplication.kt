package com.example.simple_blog

import android.app.Application
import com.example.simple_blog.di.component.DaggerAppComponent


class BlogApplication : Application() {
    val appComponent = DaggerAppComponent.builder().application(this).build()
}