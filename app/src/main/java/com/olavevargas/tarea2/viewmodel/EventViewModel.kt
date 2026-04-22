package com.olavevargas.tarea2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.olavevargas.tarea2.data.Category
import com.olavevargas.tarea2.data.Event

class EventViewModel : ViewModel(){
    var categorias by mutableStateOf(listOf<Category>())
        private set

    var eventos by mutableStateOf(listOf<Event>())
        private set


    fun addCategory(nombre: String): Int {
        val categoriaExistente = categorias.find { it.nombre.equals(nombre, ignoreCase = true) }
        if (categoriaExistente != null) {
            return categoriaExistente.id
        }
        val nuevoId = (categorias.maxOfOrNull { it.id } ?: 0) + 1
        val nuevaCategoria = Category(
            id = nuevoId,
            nombre = nombre
        )
        categorias = categorias + nuevaCategoria
        return nuevoId
    }

    fun addEvent(titulo: String, descripcion: String, idCategoria: Int) {
        val nuevoEvento = Event(
            id = eventos.size + 1,
            titulo = titulo,
            descripcion = descripcion,
            idCategoria = idCategoria
        )
        eventos = eventos + nuevoEvento
    }
    // se filtra el evento por categoria
    fun getEventsByCategory(idCategoria: Int): List<Event> {
        return eventos.filter { it.idCategoria == idCategoria }
    }

    // se obtiene evento por el id
    fun getEventById(id: Int): Event? {
        return eventos.find { it.id == id }
    }
    // para prueba
    init {
        val cat1 = Category(1, "Música")
        val cat2 = Category(2, "Tecnología")

        categorias = listOf(cat1, cat2)

        eventos = listOf(
            Event(1, "Concierto", "Evento en vivo", 1),
            Event(2, "Charla IA", "Aprende sobre IA", 2)
        )
    }

}