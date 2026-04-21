package com.olavevargas.tarea2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.olavevargas.tarea2.ui.screens.HomeScreen
import com.olavevargas.tarea2.viewmodel.EventViewModel
import com.olavevargas.tarea2.ui.screens.DetailScreen

@Composable
fun NavGraph(viewModel: EventViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"

    ) {


        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("detail/{categoryId}") { backStackEntry ->

            val categoryId = backStackEntry.arguments
                ?.getString("categoryId")
                ?.toIntOrNull() ?: 0

            DetailScreen(
                categoryId = categoryId,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}


