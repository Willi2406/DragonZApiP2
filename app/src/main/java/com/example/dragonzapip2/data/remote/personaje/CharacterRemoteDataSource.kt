package com.example.dragonzapip2.data.remote.personaje

import com.example.dragonzapip2.data.remote.DragonBallApi
import com.example.dragonzapip2.data.remote.personaje.dto.CharacterDto
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Result<List<CharacterDto>> {
        return try {
            if (!name.isNullOrBlank()) {
                val response = api.getCharactersByName(name)
                if (!response.isSuccessful) {
                    Result.failure(Exception("Error de red ${response.code()}"))
                } else {
                    Result.success(response.body() ?: emptyList())
                }
            } else {
                val response = api.getCharacters(page, limit)
                if (!response.isSuccessful) {
                    Result.failure(Exception("Error de red ${response.code()}"))
                } else {
                    Result.success(response.body()?.items ?: emptyList())
                }
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}", e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto> {
        return try {
            val response = api.getCharacterDetail(id)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else if (response.body() == null) {
                Result.failure(Exception("Personaje no encontrado."))
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