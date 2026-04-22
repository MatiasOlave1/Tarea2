package com.olavevargas.tarea2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.olavevargas.tarea2.ui.screens.HomeScreen
import com.olavevargas.tarea2.viewmodel.EventViewModel
import com.olavevargas.tarea2.ui.screens.DetailScreen
import com.olavevargas.tarea2.ui.screens.AddEventScreen

@Composable
fun NavGraph(viewModel: EventViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"

    ) {


        composable("inicio") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("detail/{idCategoria}") { backStackEntry ->

            val idCategoria = backStackEntry.arguments
                ?.getString("idCategoria")
                ?.toIntOrNull() ?: 0

            DetailScreen(
                idCategoria = idCategoria,
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("addEvent") {
            AddEventScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}