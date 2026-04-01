package com.example.pruebadesarrollador.presentation.CountryScreen

import com.example.pruebadesarrollador.domain.model.Country

sealed class CountryListUiState {
    object Loading : CountryListUiState()
    data class Success(val countries: List<Country>) : CountryListUiState()
    data class Error(val message: String) : CountryListUiState()
}