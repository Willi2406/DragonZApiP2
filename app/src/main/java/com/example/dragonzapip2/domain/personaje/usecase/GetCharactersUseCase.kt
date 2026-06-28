package com.example.dragonzapip2.domain.personaje.usecase

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.personaje.model.Character
import com.example.dragonzapip2.domain.personaje.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke(page: Int = 1, limit: Int = 50, name: String? = null): Flow<Resource<List<Character>>> {
        return repository.getCharacters(page, limit, name)
    }
}