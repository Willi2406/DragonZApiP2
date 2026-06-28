package com.example.dragonzapip2.domain.personaje.repository

import com.example.dragonzapip2.data.remote.Resource
import com.example.dragonzapip2.domain.personaje.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(page: Int, limit: Int, name: String?): Flow<Resource<List<Character>>>
    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}