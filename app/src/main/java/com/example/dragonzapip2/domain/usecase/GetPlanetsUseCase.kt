package com.example.dragonzapip2.domain.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.data.remote.dto.PlanetDto
import com.example.dragonzapip2.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val repository: PlanetRepository) {
    suspend operator fun invoke(page: Int = 1, limit: Int = 10, name: String? = null, isDestroyed: Boolean? = null): Resource<List<PlanetDto>> {
        return repository.getPlanets(page, limit, name, isDestroyed)
    }
}