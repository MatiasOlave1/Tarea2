package com.olavevargas.tarea4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olavevargas.tarea4.ui.model.EventViewModel
import com.olavevargas.tarea4.ui.Components.StyledTextField
import com.olavevargas.tarea4.R
import com.olavevargas.tarea4.ui.navigation.Home
import kotlinx.coroutines.launch

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
    var isSaving by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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

            StyledTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = stringResource(R.string.title_label),
                enabled = !isSaving
            )

            Spacer(modifier = Modifier.height(10.dp))

            StyledTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = stringResource(R.string.description_label),
                enabled = !isSaving
            )

            Spacer(modifier = Modifier.height(10.dp))

            StyledTextField(
                value = nombreCategoria,
                onValueChange = { nombreCategoria = it },
                label = stringResource(R.string.category_label),
                enabled = !isSaving
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

            if (isSaving) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else {
                Button(
                    onClick = {
                        if (titulo.isBlank() || descripcion.isBlank() || nombreCategoria.isBlank()) {
                            mensajeError = "Todos los campos son obligatorios"
                        } else {
                            coroutineScope.launch {
                                isSaving = true
                                try {
                                    val idCategoria = viewModel.addCategory(nombreCategoria)
                                    viewModel.addEvent(titulo, descripcion, idCategoria)
                                    navController.navigate(Home) {
                                        popUpTo(Home) { inclusive = true }
                                    }
                                } catch (e: Exception) {
                                    mensajeError = "Error al guardar: ${e.message}"
                                    isSaving = false
                                }
                            }
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
}
