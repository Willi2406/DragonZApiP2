package com.example.dragonzapip2.data.remote

import com.example.dragonzapip2.data.remote.planet.dto.PlanetDto
import com.example.dragonzapip2.data.remote.planet.dto.PlanetsResponseDto
import com.example.dragonzapip2.data.remote.personaje.dto.CharacterDto
import com.example.dragonzapip2.data.remote.personaje.dto.CharactersResponseDto
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

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<CharactersResponseDto>

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String
    ): Response<List<CharacterDto>>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<CharacterDto>
}
