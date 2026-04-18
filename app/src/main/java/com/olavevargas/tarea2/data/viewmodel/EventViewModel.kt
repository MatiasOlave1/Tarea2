package com.olavevargas.tarea2.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.olavevargas.tarea2.data.categoria
import com.olavevargas.tarea2.data.Event

class EventViewModel : ViewModel(){
    var categories by mutableStateOf(listOf<categoria>())
        private set


}