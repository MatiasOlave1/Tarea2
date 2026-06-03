package com.olavevargas.tarea3.data.remote

import com.olavevargas.tarea3.data.remote.dto.CategoryDto
import com.olavevargas.tarea3.data.remote.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventMasterApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @POST("categories")
    suspend fun createCategory(@Body category: CategoryDto): CategoryDto

    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") id: Int): CategoryDto

    @GET("events")
    suspend fun getEvents(): List<EventDto>

    @POST("events")
    suspend fun createEvent(@Body event: EventDto): EventDto

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: Int): EventDto
}