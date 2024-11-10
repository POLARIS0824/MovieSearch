package com.example.movie.Network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Callback

class ApiService {
    private val apiKey = "b862c432"
    private val client = OkHttpClient()

    // 搜索电影
    fun searchMovies(query: String, callback: Callback) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&s=$query"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }

    /*
    // 获取电影详情
    fun getMovieDetails(movieId: String, callback: Callback) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&i=$movieId"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(callback)
    }
     */
}
