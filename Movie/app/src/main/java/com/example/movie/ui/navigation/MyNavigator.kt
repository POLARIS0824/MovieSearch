package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.pages.MovieSearchPage

@Composable
fun MyNavigator(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteName.HISTORY_PAGE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(startDestination){
            MovieSearchPage()
        }
        composable(RouteName.HISTORY_PAGE){}

    }
}