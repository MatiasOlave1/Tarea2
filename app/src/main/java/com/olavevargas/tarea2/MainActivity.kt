package com.olavevargas.tarea2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.olavevargas.tarea2.ui.theme.Tarea2Theme
import com.olavevargas.tarea2.viewmodel.EventViewModel
import com.olavevargas.tarea2.navigation.NavGraph
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tarea2Theme {
                val viewModel: EventViewModel = viewModel()
                NavGraph(viewModel)
            }
        }
    }
}

