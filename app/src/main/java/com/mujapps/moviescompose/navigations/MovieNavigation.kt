package com.mujapps.moviescompose.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mujapps.moviescompose.screens.detail.DetailScreen
import com.mujapps.moviescompose.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MoviesScreens.HomeScreen.name
    ) {
        composable(MoviesScreens.HomeScreen.name) {
            //Here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        //Mimics navigation in the web like behavior
        //{movie} -> movie is an argument variable
        composable(MoviesScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEntry ->
            //Here we pass where this should lead us to
            DetailScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}