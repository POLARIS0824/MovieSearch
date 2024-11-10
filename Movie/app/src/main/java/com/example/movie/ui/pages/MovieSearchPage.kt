package com.example.movie.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.model.Movie
import com.example.movie.ui.components.MovieList
import com.example.movie.ui.components.SearchBar
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.viewModel.MovieViewModel



@Composable
fun MovieSearchPage(movieViewModel: MovieViewModel = viewModel()) {
    val query by movieViewModel.queryText.observeAsState("")  // 使用observeAsState来观察queryText
    val movies by movieViewModel.movieList.observeAsState(emptyList())  // 使用observeAsState来观察movieList

    /*
    // 确保每次query更新时都会调用searchMovies
    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            movieViewModel.searchMovies(query)
        }
    }
     */

    MovieSearchPageImpl(
        query = query,
        onQueryChanged = { newQuery -> movieViewModel.updateQueryText(newQuery) },  // 更新查询文本
        movies = movies
    )

    // 当查询文本变化时，进行电影搜索
    if (query.isNotEmpty()) {
        movieViewModel.searchMovies(query)
    }
}

@Composable
fun MovieSearchPageImpl(
    query: String,
    onQueryChanged: (String) -> Unit = {},
    movies: List<Movie> = listOf()
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                query,
                onQueryChanged
            ) {
            }
            /*
            MovieList(movies = movies.filter {
                it.title.contains(query, ignoreCase = true)
            })
             */
            MovieList(movies = movies)  // 直接显示传入的电影列表，不需要再次过滤
            /*
            it 是 kotlin 在单个参数中的隐式命名
            在 SearchBar 组件中，onQueryChanged 是一个函数类型的参数，
            它的作用是当用户在搜索框中输入内容时，更新 query 的值。
            具体来说，当用户输入内容时，onValueChange 会触发并传入新的值（即输入框中的内容）。
            此时我们把这个值传递给 onQueryChanged，也就是 query = it
             */
        }
    }
}

@Preview
@Composable
fun MovieSearchPagePreview() {
    MovieTheme {
        MovieSearchPageImpl(
            "",
            {},
            listOf(
                Movie(
                    "tt0468569", "The Dark Knight", "https://images.app.goo.gl/p2T8Dhnbrrrspqmp6",
                    "2008", "Action", "Christopher Nolan", "American"
                ),
                Movie(
                    "tt0468569", "The Dark Knight", "https://images.app.goo.gl/p2T8Dhnbrrrspqmp6",
                    "2008", "Action", "Christopher Nolan", "American"
                ),
                Movie(
                    "tt0468569", "The Dark Knight", "https://images.app.goo.gl/p2T8Dhnbrrrspqmp6",
                    "2008", "Action", "Christopher Nolan", "American"
                ), Movie(
                    "tt0468569", "The Dark Knight", "https://images.app.goo.gl/p2T8Dhnbrrrspqmp6",
                    "2008", "Action", "Christopher Nolan", "American"
                )
            )
        )
        // MovieCard(movie = Movie("tt0468569", "The Dark Knight", "https://images.app.goo.gl/p2T8Dhnbrrrspqmp6", "2008"))
    }
}
