package com.olavevargas.tarea3.data.remote.repository

import com.olavevargas.tarea3.data.local.entity.Event
import com.olavevargas.tarea3.data.remote.EventMasterApi
import com.olavevargas.tarea3.data.remote.dto.toDto
import com.olavevargas.tarea3.data.remote.dto.toEntity
import com.olavevargas.tarea3.data.repository.Event.EventRepository
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
        // TODO: Implement delete in API if necessary.
    }

    override suspend fun updateEvent(event: Event) {
        // TODO: Implement update in API if necessary.
    }
}