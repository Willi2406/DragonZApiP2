package com.example.dragonzapip2.presentacion.list

sealed interface PlanetListEvent {
    data class UpdateFilters(val name: String, val isDestroyed: Boolean? = null) : PlanetListEvent
    data object Search: PlanetListEvent
}