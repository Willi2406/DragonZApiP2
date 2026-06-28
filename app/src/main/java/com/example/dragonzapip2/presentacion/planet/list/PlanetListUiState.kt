package com.example.dragonzapip2.presentacion.planet.list

import com.example.dragonzapip2.domain.planet.model.Planet

data class PlanetListUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterIsDestroyed: Boolean? = null
)