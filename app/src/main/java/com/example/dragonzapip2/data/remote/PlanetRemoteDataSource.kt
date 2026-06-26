package com.example.dragonzapip2.data.remote

import com.example.dragonzapip2.data.remote.dto.PlanetDto
import com.example.dragonzapip2.data.remote.dto.PlanetsResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Result<PlanetsResponseDto> {
        return try {
            val response = api.getPlanets(page, limit, name, isDestroyed)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else if (response.body() == null) {
                Result.failure(Exception("Cuerpo de respuesta vacío"))
            } else {
                Result.success(response.body()!!)
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else if (response.body() == null) {
                Result.failure(Exception("Planeta no encontrado."))
            } else {
                Result.success(response.body()!!)
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}", e))
        }
    }
}