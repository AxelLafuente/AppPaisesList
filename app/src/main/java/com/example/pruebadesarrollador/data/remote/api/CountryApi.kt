package com.example.pruebadesarrollador.data.remote.api

import com.example.pruebadesarrollador.data.remote.dto.CountryDetailDto
import com.example.pruebadesarrollador.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("v3.1/all?fields=name,capital,flags")
    suspend fun getCountries(): List<CountryDto>

    @GET("v3.1/name/{name}")
    suspend fun searchByName(
        @Path("name") name: String
    ): List<CountryDto>

    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): List<CountryDetailDto>
}