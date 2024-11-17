package com.example.movie.viewModel

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coil3.util.Logger
import com.example.movie.Network.ApiService
import com.example.movie.model.Movie
import com.example.movie.model.MovieSearchResponse
import com.google.gson.Gson
import okhttp3.Callback
import java.io.IOException

class MovieViewModel : ViewModel() {
    private val apiService = ApiService()

    private val _movieList = MutableLiveData<List<Movie>>()  // 私有变量
    val movieList: MutableLiveData<List<Movie>> get() = _movieList
    // 通过 getter 方法暴露 LiveData 对象，但不允许外部修改，只能在 ViewModel 内部修改（封装）

    // 用 LiveData 存储查询文本
    private val _queryText = MutableLiveData("")
    val queryText: LiveData<String> get() = _queryText

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> get() = _errorMessage

    /*
    // 更新电影列表
    fun updateMovies(movies: List<Movie>) {
        _movieList.value = movies
    }
     */

    // 更新查询文本
    fun updateQueryText(query: String) {
        _queryText.value = query
    }

    fun searchMovies(query: String) {
        apiService.searchMovies(query, object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // Handle error
                _errorMessage.postValue("Error: ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    if (responseBody.isNullOrEmpty()) {
                        _errorMessage.postValue("Response body is null")
                        return
                    }
                    val gson = Gson()
                    val movieSearchResponse = gson.fromJson(responseBody, MovieSearchResponse::class.java)

                    if (movieSearchResponse?.Response == "True") {
                        val movies = movieSearchResponse?.search?.map { movie ->
                            Movie(
                                imdbID = movie.imdbID,
                                title = movie.title,
                                poster = movie.poster,  // 如果 poster 是 null，使用空字符串
                                year = movie.year ?: "Unknown",  // 如果 year 是 null，显示 "Unknown"
                                genre = movie.genre ?: "Not Available",  // 如果 genre 是 null，显示 "Not Available"
                                director = movie.director ?: "Unknown",  // 如果 director 是 null，显示 "Unknown"
                                country = movie.country ?: "Unknown"  // 如果 country 是 null，显示 "Unknown"
                            )
                        } ?: emptyList()
                        _movieList.postValue(movies)
                    } else {
                        _errorMessage.postValue("No results found")
                    }
                } else {
                    _errorMessage.postValue("Request failed with status: ${response.code}")
                    // Handle error
                }
            }
        })
    }
}
