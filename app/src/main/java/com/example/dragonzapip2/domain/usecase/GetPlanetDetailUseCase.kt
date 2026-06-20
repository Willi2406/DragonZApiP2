package com.example.dragonzapip2.domain.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.data.remote.dto.PlanetDto
import com.example.dragonzapip2.domain.repository.PlanetRepository
import javax.inject.Inject


class GetPlanetsDetailUseCase @Inject constructor(private val repository: PlanetRepository) {
    suspend operator fun invoke(id: Int): Resource<PlanetDto> {
        return repository.getPlanetDetail(id)
    }
}