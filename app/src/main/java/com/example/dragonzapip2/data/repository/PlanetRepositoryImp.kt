package com.example.dragonzapip2.data.repository

import com.example.dragonzapip2.data.remote.DragonBallApi
import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.repository.PlanetRepository
import javax.inject.Inject
import com.example.dragonzapip2.data.remote.dto.PlanetDto

class PlanetRepositoryImp @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository {

    override suspend fun getPlanets(page: Int, limit: Int, name: String?, isDestroyed: Boolean?): Resource<List<PlanetDto>> {
        return try {
            val response = api.getPlanets(page, limit, name, isDestroyed)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.items
                Resource.Success(data)
            } else {
                Resource.Error("Error del servidor: ${response.message()}")
            }
        } catch (e: Exception) {
            if (!name.isNullOrBlank()) {
                try {
                    val alternativeResponse = api.getPlanets(page = 1, limit = 50, name = null, isDestroyed = null)
                    if (alternativeResponse.isSuccessful && alternativeResponse.body() != null) {
                        val filteredList = alternativeResponse.body()!!.items.filter {
                            it.name.contains(name, ignoreCase = true)
                        }
                        Resource.Success(filteredList)
                    } else {
                        Resource.Error("Error al filtrar localmente.")
                    }
                } catch (nestedException: Exception) {
                    Resource.Error("Error en filtro: ${nestedException.localizedMessage}")
                }
            } else {
                Resource.Error("Error de conexión: ${e.localizedMessage}")
            }
        }
    }

    override suspend fun getPlanetDetail(id: Int): Resource<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Planeta no encontrado.")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }
}