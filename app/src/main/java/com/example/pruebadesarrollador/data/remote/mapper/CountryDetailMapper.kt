package com.example.pruebadesarrollador.data.remote.mapper

import com.example.pruebadesarrollador.data.remote.dto.CountryDetailDto
import com.example.pruebadesarrollador.domain.model.CountryDetail

import java.text.NumberFormat
import java.util.Locale

fun CountryDetailDto.toDomain(): CountryDetail {
    val formattedPopulation = population?.let {
        NumberFormat.getInstance(Locale.US).format(it)
    } ?: "N/A"

    val formattedArea = area?.let {
        "${NumberFormat.getInstance(Locale.US).format(it)} km²"
    } ?: "N/A"

    return CountryDetail(
        name = name.common,
        officialName = name.official,
        flagUrl = flags.svg ?: "",
        coatOfArmsUrl = coatOfArms?.svg,
        region = region ?: "N/A",
        subregion = subregion ?: "N/A",
        capital = capital?.joinToString() ?: "N/A",
        area = formattedArea,
        population = formattedPopulation,
        languages = languages?.values?.joinToString() ?: "N/A",
        driverSide = car?.side ?: "N/A",
        currencies = currencies?.values
            ?.mapNotNull { currency ->
                when {
                    !currency.name.isNullOrBlank() && !currency.symbol.isNullOrBlank() ->
                        "${currency.name} (${currency.symbol})"
                    !currency.name.isNullOrBlank() -> currency.name
                    else -> null
                }
            }
            ?.joinToString()
            ?: "N/A",
        timezone = timezones?.joinToString() ?: "N/A"
    )
}