package com.example.pruebadesarrollador.data.remote.dto

data class CountryDetailDto(
    val name: NameDto,
    val flags: FlagsDto,
    val coatOfArms: CoatOfArmsDto?,
    val region: String?,
    val subregion: String?,
    val capital: List<String>?,
    val area: Double?,
    val population: Long?,
    val languages: Map<String, String>?,
    val car: CarDto?,
    val currencies: Map<String, CurrencyDto>?,
    val timezones: List<String>?
)

data class CoatOfArmsDto(
    val svg: String?
)

data class CarDto(
    val side: String?
)

data class CurrencyDto(
    val name: String?,
    val symbol: String?
)