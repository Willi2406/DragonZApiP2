package com.example.dragonzapip2.domain.personaje.model

import com.example.dragonzapip2.domain.planet.model.Planet

data class Character(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val originPlanet: Planet? = null,
    val transformations: List<Transformation> = emptyList()
)
