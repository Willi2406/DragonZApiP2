package com.example.dragonzapip2.data.remote.personaje.dto

import com.example.dragonzapip2.data.remote.planet.dto.PlanetDto
import com.example.dragonzapip2.domain.personaje.model.Character
import com.example.dragonzapip2.domain.personaje.model.Transformation
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponseDto(val items: List<CharacterDto>)

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val originPlanet: PlanetDto? = null,
    val transformations: List<TransformationDto>? = null
) {
    fun toDomain() = Character(
        id = id,
        name = name,
        ki = ki,
        maxKi = maxKi,
        race = race,
        gender = gender,
        description = description,
        image = image,
        originPlanet = originPlanet?.toDomain(),
        transformations = transformations?.map { it.toDomain() } ?: emptyList()
    )
}

@JsonClass(generateAdapter = true)
data class TransformationDto(
    val id: Int,
    val name: String,
    val image: String,
    val ki: String
) {
    fun toDomain() = Transformation(id, name, image, ki)
}