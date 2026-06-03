package com.olavevargas.tarea4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.olavevargas.tarea4.ui.model.CategoryViewModel
import com.olavevargas.tarea4.ui.model.EventViewModel
import com.olavevargas.tarea4.ui.navigation.Navigation
import com.olavevargas.tarea4.ui.theme.Tarea2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            Tarea2Theme {

                val eventViewModel: EventViewModel =
                    hiltViewModel()

                val categoryViewModel: CategoryViewModel =
                    hiltViewModel()

                Navigation(
                    viewModel = eventViewModel,
                    categoryViewModel = categoryViewModel
                )
            }
        }
    }
}