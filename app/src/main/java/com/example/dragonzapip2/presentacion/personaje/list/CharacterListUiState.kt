package com.example.dragonzapip2.presentacion.personaje.list

import com.example.dragonzapip2.domain.personaje.model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)