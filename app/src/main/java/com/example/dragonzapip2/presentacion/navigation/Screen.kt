package com.example.dragonzapip2.presentacion.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object PlanetList : Screen()

    @Serializable
    data class PlanetDetail(val id: Int) : Screen()
}