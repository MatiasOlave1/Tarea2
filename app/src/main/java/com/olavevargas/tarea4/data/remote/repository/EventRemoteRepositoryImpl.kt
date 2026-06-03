package com.olavevargas.tarea4.data.remote.repository

import com.olavevargas.tarea4.data.local.entity.Event
import com.olavevargas.tarea4.data.remote.EventMasterApi
import com.olavevargas.tarea4.data.remote.dto.toDto
import com.olavevargas.tarea4.data.remote.dto.toEntity
import com.olavevargas.tarea4.data.repository.Event.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRemoteRepositoryImpl @Inject constructor(
    private val api: EventMasterApi
) : EventRepository {

    override fun getAllEventsStream(): Flow<List<Event>> = flow {
        val events = api.getEvents().map { it.toEntity() }
        emit(events)
    }

    override fun getEventsByCategoryStream(categoryId: Int): Flow<List<Event>> = flow {
        val events = api.getEvents()
            .filter { it.category_id == categoryId }
            .map { it.toEntity() }
        emit(events)
    }

    override suspend fun insertEvent(event: Event) {
        api.createEvent(event.toDto())
    }

    override suspend fun deleteEvent(event: Event) {
        // aca deberiamos implementar la funcion delete si fuera necesaria
    }

    override suspend fun updateEvent(event: Event) {
        // aca deberiamos implementar la funcion update si fuera necesaria
    }
}