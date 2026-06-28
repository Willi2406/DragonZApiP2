package com.example.dragonzapip2.presentacion.personaje.detail

import com.example.dragonzapip2.domain.personaje.model.Character

data class DetailCharacterUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)