package com.example.dragonzapip2.presentacion.personaje.list

sealed interface CharacterListEvent {
    data class UpdateFilters(val name: String) : CharacterListEvent
    data object Search : CharacterListEvent
}