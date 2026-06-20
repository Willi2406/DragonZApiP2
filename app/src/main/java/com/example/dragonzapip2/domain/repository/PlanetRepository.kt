package com.example.dragonzapip2.domain.repository

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.data.remote.dto.PlanetDto

interface PlanetRepository {
    suspend fun getPlanets(page: Int, limit: Int, name: String?, isDestroyed: Boolean?): Resource<List<PlanetDto>>
    suspend fun getPlanetDetail(id: Int): Resource<PlanetDto>
}