package com.olavevargas.tarea4.data.repository.Event

import com.olavevargas.tarea4.data.local.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getAllEventsStream(): Flow<List<Event>>
    fun getEventsByCategoryStream(categoryId: Int): Flow<List<Event>>
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(event: Event)
    suspend fun updateEvent(event: Event)
}
