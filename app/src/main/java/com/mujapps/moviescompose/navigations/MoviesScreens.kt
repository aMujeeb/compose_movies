package com.mujapps.moviescompose.navigations

enum class MoviesScreens {
    HomeScreen,
    DetailScreen;

    companion object { //routes are like web urls
        fun fromRoute(route: String?): MoviesScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route Not Exists")
        }
    }
}