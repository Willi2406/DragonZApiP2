package com.example.dragonzapip2.domain.planet.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.planet.model.Planet
import com.example.dragonzapip2.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(private val repository: PlanetRepository) {
    operator fun invoke(id: Int): Flow<Resource<Planet>> {
        return repository.getPlanetDetail(id)
    }
}