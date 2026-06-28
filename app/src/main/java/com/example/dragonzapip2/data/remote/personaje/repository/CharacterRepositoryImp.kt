package com.example.dragonzapip2.data.remote.personaje.repository

import com.example.dragonzapip2.data.remote.personaje.CharacterRemoteDataSource
import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.personaje.model.Character
import com.example.dragonzapip2.domain.personaje.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getCharacters(page, limit, name)
        response.onSuccess { dtoList ->
            emit(Resource.Success(dtoList.map { it.toDomain() }))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido"))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getCharacterDetail(id)
        response.onSuccess { characterDto ->
            emit(Resource.Success(characterDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido"))
        }
    }
}