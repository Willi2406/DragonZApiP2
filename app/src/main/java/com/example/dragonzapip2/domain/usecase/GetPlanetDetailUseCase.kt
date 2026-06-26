package com.example.dragonzapip2.domain.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.model.Planet
import com.example.dragonzapip2.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(private val repository: PlanetRepository) {
    operator fun invoke(id: Int): Flow<Resource<Planet>> {
        return repository.getPlanetDetail(id)
    }
}