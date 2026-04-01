package com.example.pruebadesarrollador.domain.model

data class CountryDetail(
    val name: String,
    val officialName: String,
    val flagUrl: String,
    val coatOfArmsUrl: String?,
    val region: String,
    val subregion: String,
    val capital: String,
    val area: String,
    val population: String,
    val languages: String,
    val driverSide: String,
    val currencies: String,
    val timezone: String,
)