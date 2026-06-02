package com.olavevargas.tarea4.data.remote.dto

data class EventDto(
    val id: Int? = null,
    val titulo: String,
    val descripcion: String,
    val category_id: Int
)