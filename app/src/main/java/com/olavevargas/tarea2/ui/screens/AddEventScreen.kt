package com.olavevargas.tarea2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olavevargas.tarea2.viewmodel.EventViewModel
import com.olavevargas.tarea2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    viewModel: EventViewModel,
    navController: NavController,
) {

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var nombreCategoria by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_event_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text(stringResource(R.string.title_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text(stringResource(R.string.description_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = nombreCategoria,
                onValueChange = { nombreCategoria = it },
                label = { Text(stringResource(R.string.category_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // error
            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (titulo.isBlank() || descripcion.isBlank() || nombreCategoria.isBlank()) {
                        mensajeError = "Todos los campos son obligatorios"
                    } else {
                        // Se crea u obtiene la categoría personalizada
                        val idCategoria = viewModel.addCategory(nombreCategoria)
                        
                        viewModel.addEvent(
                            titulo,
                            descripcion,
                            idCategoria
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(stringResource(R.string.save_button))
            }
        }
    }
}
