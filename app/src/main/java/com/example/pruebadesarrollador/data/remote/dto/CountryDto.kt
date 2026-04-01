package com.example.pruebadesarrollador.data.remote.dto

data class CountryDto(
    val name: NameDto,
    val capital: List<String>?,
    val flags: FlagsDto
)

data class NameDto(
    val common: String,
    val official: String
)

data class FlagsDto(
    val svg: String?
)