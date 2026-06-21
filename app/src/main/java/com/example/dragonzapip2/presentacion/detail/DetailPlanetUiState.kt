package com.example.dragonzapip2.presentacion.detail

import com.example.dragonzapip2.domain.model.Planet

data class DetailPlanetUiState (
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)