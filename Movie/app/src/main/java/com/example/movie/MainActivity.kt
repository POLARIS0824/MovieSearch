package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.ui.pages.MovieSearchPage
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 获取 MovieViewModel 实例
            val movieViewModel: MovieViewModel = viewModel()

            // 使用 MovieTheme 包裹 MovieSearchPage
            MovieTheme {
                // 在此传递 MovieViewModel 到 MovieSearchPage
                MovieSearchPage(movieViewModel = movieViewModel)
            }
        }
    }



}
