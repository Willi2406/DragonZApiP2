package com.example.dragonzapip2.domain.personaje.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.personaje.model.Character
import com.example.dragonzapip2.domain.personaje.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke(id: Int): Flow<Resource<Character>> {
        return repository.getCharacterDetail(id)
    }
}