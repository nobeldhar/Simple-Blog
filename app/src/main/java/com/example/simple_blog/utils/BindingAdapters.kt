package com.example.simple_blog.utils

import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation


@BindingAdapter(value = ["bind:image_url"])
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        view.load(url) {
            crossfade(true)
            crossfade(300)
            transformations(RoundedCornersTransformation(radius = 10f))
        }
        Log.d("TAG", "loadImage: url:$url")
    }
}

@BindingAdapter(value = ["bind:circular_image_url"])
fun loadCircularImage(view: ImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        view.load(url) {
            crossfade(true)
            crossfade(300)
            transformations(CircleCropTransformation())
        }
        Log.d("TAG", "loadImage: url:$url")
    }
}



