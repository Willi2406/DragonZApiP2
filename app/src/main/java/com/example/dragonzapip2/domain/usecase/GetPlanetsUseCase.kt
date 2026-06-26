package com.example.dragonzapip2.domain.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.model.Planet
import com.example.dragonzapip2.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val repository: PlanetRepository) {
    operator fun invoke(page: Int = 1, limit: Int = 10, name: String? = null, isDestroyed: Boolean? = null): Flow<Resource<List<Planet>>> {
        return repository.getPlanets(page, limit, name, isDestroyed)
    }
}