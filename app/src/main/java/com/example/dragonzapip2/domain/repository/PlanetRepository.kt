package com.example.dragonzapip2.domain.repository

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(page: Int, limit: Int, name: String?, isDestroyed: Boolean?): Flow<Resource<List<Planet>>>
    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}