package com.projet.applicationcinema.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.projet.applicationcinema.screens.home.HomeScreen
import com.projet.applicationcinema.screens.details.DetailsScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name ){
        composable(route= MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(route= MovieScreens.DetailsScreen.name
                    +"/{movieName}", arguments = listOf(navArgument(name = "movieName"){type = NavType.StringType})){
            backStackEntry ->
            DetailsScreen(navController = navController,backStackEntry.arguments?.getString("movieName"))
        }
    }
}