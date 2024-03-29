package com.app.taazanews.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun TaazaNews(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ){
        composable(route = Screens.Home.route){
            NewsCategory(navController = navController)
        }
        composable(
            route = Screens.NewsScreen.route,
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                }
            )
        ){
            val name = it.arguments?.getString("name")
            NewsDetailScreen(navController = navController, mUrl = name!!)
        }
    }
}

sealed class Screens(val route: String){
    object Home: Screens("Home")
    object NewsScreen: Screens("News/{name}")
}

