package com.projet.applicationcinema.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.projet.applicationcinema.MyApp
import com.projet.applicationcinema.model.Movie
import com.projet.applicationcinema.model.getMovies
import com.projet.applicationcinema.navigation.MovieNavigation
import com.projet.applicationcinema.navigation.MovieScreens
import com.projet.applicationcinema.widgets.MovieRow

@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta,
                elevation = 5.dp
            ) {
                Text(text = "LE CINEMA")
            }
        }
    ) {
        MainContent(navController=navController)
    }

}

@Composable
fun MainContent(
    navController: NavController,
    movieList : List<Movie> = getMovies()
){

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(items=movieList){
                MovieRow(it){
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$it")
                    Log.d("tag",it)
                }
            }
        }
    }
}








@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}