package com.example.dragonzapip2.data.remote

import com.example.dragonzapip2.data.remote.dto.PlanetDto
import com.example.dragonzapip2.data.remote.dto.PlanetsResponseDto
import com.squareup.moshi.JsonClass
import com.example.dragonzapip2.domain.model.Planet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {
    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("isDestroyed") isDestroyed: Boolean?
    ): Response<PlanetsResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(@Path("id") id: Int): Response<PlanetDto>
}
