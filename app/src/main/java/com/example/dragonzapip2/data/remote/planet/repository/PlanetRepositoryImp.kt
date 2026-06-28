package com.example.dragonzapip2.data.remote.planet.repository

import com.example.dragonzapip2.data.remote.planet.PlanetRemoteDataSource
import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.planet.model.Planet
import com.example.dragonzapip2.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImp @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planet>>> = flow {

        emit(Resource.Loading())

        val response = remoteDataSource.getPlanets(page, limit, name, isDestroyed)

        response.onSuccess { planetsResponse ->
            emit(Resource.Success(planetsResponse.items.map { it.toDomain() }))
        }.onFailure { exception ->
            if (!name.isNullOrBlank()) {
                val alternativeResponse = remoteDataSource.getPlanets(page = 1, limit = 50, name = null, isDestroyed = null)
                alternativeResponse.onSuccess { allPlanets ->
                    val filteredList = allPlanets.items
                        .filter { it.name.contains(name, ignoreCase = true) }
                        .map { it.toDomain() }
                    emit(Resource.Success(filteredList))
                }.onFailure {
                    emit(Resource.Error("Error al filtrar localmente."))
                }
            } else {
                emit(Resource.Error(exception.message ?: "Error desconocido"))
            }
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getPlanetDetail(id)

        response.onSuccess { planetDto ->
            emit(Resource.Success(planetDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido"))
        }
    }
}