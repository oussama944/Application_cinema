package com.projet.applicationcinema.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.projet.applicationcinema.model.Movie
import com.projet.applicationcinema.model.getMovies
import com.projet.applicationcinema.widgets.MovieRow

@Composable
fun DetailsScreen(
    navController: NavController,
    movieID: String?
){
    val oneMovie = getMovies().filter { movie ->
        movie.id == movieID
    }
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Cyan,
            elevation = 5.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "info",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })

                Spacer(modifier = Modifier.width(100.dp))

                Text(text = "LE CINEMA")
            }

        }
    }) {
            Surface(modifier= Modifier
                .fillMaxHeight()
                .fillMaxWidth()){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {
            MovieRow(movieName = oneMovie.first())
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Les images du film")
            HorizontalScroableImageView(oneMovie)
            }
        }
    }
}

@Composable
private fun HorizontalScroableImageView(oneMovie: List<Movie>) {
    LazyRow() {
        items(oneMovie.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Poster du film"
                )
            }
        }
    }
}