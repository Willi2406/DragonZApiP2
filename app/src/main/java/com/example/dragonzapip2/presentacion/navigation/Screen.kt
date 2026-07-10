package com.example.dragonzapip2.presentacion.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object PlanetList : Screen()
    @Serializable
    data class PlanetDetail(val id: Int) : Screen()

    @Serializable
    data object CharacterList : Screen()
    @Serializable
    data class CharacterDetail(val id: Int) : Screen()
}