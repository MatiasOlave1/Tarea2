package com.olavevargas.tarea3.data.remote.dto

import kotlinx.serialization.Serializable
import com.olavevargas.tarea3.data.local.entity.Event

@Serializable
data class EventDto(
    val id: Int? = null,
    val titulo: String,
    val descripcion: String,
    val category_id: Int,
    val category: CategoryDto? = null
)

fun EventDto.toEntity(): Event {
    return Event(
        id = id ?: 0,
        titulo = titulo,
        descripcion = descripcion,
        idCategoria = category_id
    )
}

fun Event.toDto(): EventDto {
    return EventDto(
        id = if (id == 0) null else id,
        titulo = titulo,
        descripcion = descripcion,
        category_id = idCategoria
    )
}