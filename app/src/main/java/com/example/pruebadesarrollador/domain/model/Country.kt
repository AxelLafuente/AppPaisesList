package com.example.pruebadesarrollador.domain.model

import com.example.pruebadesarrollador.data.remote.dto.CountryDto

data class Country(
    val common: String,
    val official: String,
    val capital: String,
    val flagUrl: String,
    val isFavorite: Boolean = false
)

fun CountryDto.toDomain(): Country {
    return Country(
        common = name.common,
        official = name.official,
        capital = capital?.firstOrNull() ?: "N/A",
        flagUrl = flags.svg ?: "",
        isFavorite = false
    )
}