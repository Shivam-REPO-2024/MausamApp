package com.chakravyuh.mausam.navigation

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chakravyuh.mausam.Screens.HomeScreen
import com.chakravyuh.mausam.Screens.WeeklyReportScreen
import com.chakravyuh.mausam.location.LocationUtils
import com.chakravyuh.mausam.model.WeatherResponseState
import com.chakravyuh.mausam.viewModels.LocationViewModel
import com.chakravyuh.mausam.viewModels.WeatherViewModel

@Composable
fun Navigation(

    context : Context,
    locationUtils: LocationUtils,
    locationViewModel: LocationViewModel,
   weatherViewModel: WeatherViewModel,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(Screens.HomeScreen.route,
//            enterTransition = { slideInHorizontally (initialOffsetX = {it}, animationSpec = tween(500)) },
            enterTransition = { slideInHorizontally (initialOffsetX = {it}, animationSpec = tween(500)) },

            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
            }

        ){
            HomeScreen(
                context = context,
                locationviewModel = locationViewModel,
                locationUtils = locationUtils,
                weatherViewModel = weatherViewModel,
                navController = navController
            )
        }
        composable(Screens.WeeklyReportScreen.route,
            enterTransition = { slideInHorizontally (initialOffsetX = {it}, animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
            }
            ){
            WeeklyReportScreen(navController = navController,weatherViewModel,locationViewModel)
        }

    }

}