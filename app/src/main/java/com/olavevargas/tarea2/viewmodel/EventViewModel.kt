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


    fun addCategory(name: String): Int {
        val existing = categorias.find { it.name.equals(name, ignoreCase = true) }
        if (existing != null) return existing.id

        val newId = if (categorias.isEmpty()) 1 else categorias.maxOf { it.id } + 1
        val newCategory = Category(
            id = newId,
            name = name
        )
        categorias = categorias + newCategory
        return newId
    }

    fun addEvent(title: String, description: String, categoryId: Int) {
        val newEvent = Event(
            id = eventos.size + 1,
            title = title,
            description = description,
            catId = categoryId
        )
        eventos = eventos + newEvent
    }
    // se filtra el evento por categoria
    fun getEventsByCategory(categoryId: Int): List<Event> {
        return eventos.filter { it.catId == categoryId }
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