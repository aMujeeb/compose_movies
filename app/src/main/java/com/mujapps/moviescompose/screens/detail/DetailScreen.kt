package com.mujapps.moviescompose.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mujapps.moviescompose.models.Movie
import com.mujapps.moviescompose.models.getMovies
import com.mujapps.moviescompose.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {

    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 4.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Bck",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = newMovieList[0].title ?: "No movie",
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }) {
        it.calculateBottomPadding()
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = newMovieList[0])
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(
                    text = "Movie Images",
                    style = TextStyle(
                        color = Color.Yellow,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                HorizontalScrollerImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollerImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .size(160.dp), elevation = 4.dp
            ) {
                AsyncImage(
                    model = it,
                    contentDescription = "Movie Posters"
                )
            }
        }
    }
}