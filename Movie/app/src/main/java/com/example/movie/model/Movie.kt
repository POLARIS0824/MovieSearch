package com.example.movie.model

import com.google.gson.annotations.SerializedName

data class Movie (
    val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Year") val year: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Director") val director: String?,
    @SerializedName("Country") val country: String?,
)

// 在 Kotlin 中，构造函数参数会自动成为类的属性，且不需要显式地定义 getter 和 setter 方法