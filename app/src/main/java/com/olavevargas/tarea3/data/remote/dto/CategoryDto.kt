package com.olavevargas.tarea3.data.remote.dto

import kotlinx.serialization.Serializable
import com.olavevargas.tarea3.data.local.entity.Category

@Serializable
data class CategoryDto(
    val id: Int? = null,
    val nombre: String
)

fun CategoryDto.toEntity(): Category {
    return Category(
        id = id ?: 0,
        nombre = nombre
    )
}

fun Category.toDto(): CategoryDto {
    return CategoryDto(
        id = if (id == 0) null else id,
        nombre = nombre
    )
}