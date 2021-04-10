package com.example.simple_blog.data.remote.response


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("profession")
    var profession: String?
)