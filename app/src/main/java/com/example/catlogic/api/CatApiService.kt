package com.example.catlogic.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CatApiService {
    @GET("api/logs/eating/")
    suspend fun getEatingLogs(): List<EatingActivity>

    @GET("api/logs/playing")
    suspend fun getPlayingLogs(): List<PlayingActivity>

    @GET("api/logs/health/")
    suspend fun getHealthLogs(): List<HealthActivity>

    @POST("api/logs/eating/")
    suspend fun postEatingLog(@Body log: EatingActivity): Response<Unit>

    @POST("api/logs/playing/")
    suspend fun postPlayingLog(@Body log: PlayingActivity): Response<Unit>

    @POST("api/logs/health/")
    suspend fun postHealthLog(@Body log: HealthActivity): Response<Unit>
}