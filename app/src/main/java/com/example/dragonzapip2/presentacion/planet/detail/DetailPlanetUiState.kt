package com.example.dragonzapip2.presentacion.planet.detail

import com.example.dragonzapip2.domain.planet.model.Planet

data class DetailPlanetUiState (
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)