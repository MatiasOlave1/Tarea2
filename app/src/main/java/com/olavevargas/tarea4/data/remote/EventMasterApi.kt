package com.olavevargas.tarea4.data.remote

import com.olavevargas.tarea4.data.remote.dto.CategoryDto
import com.olavevargas.tarea4.data.remote.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventMasterApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") id: Int): CategoryDto

    @GET("events")
    suspend fun getEvents(): List<EventDto>

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: Int): EventDto

    @POST("categories")
    suspend fun createCategory(
        @Body category: CategoryDto
    ): CategoryDto

    @POST("events")
    suspend fun createEvent(
        @Body event: EventDto
    ): EventDto
}