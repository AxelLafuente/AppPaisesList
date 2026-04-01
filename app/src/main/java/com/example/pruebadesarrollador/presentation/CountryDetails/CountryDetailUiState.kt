package com.example.pruebadesarrollador.presentation.CountryDetails

import com.example.pruebadesarrollador.domain.model.CountryDetail

sealed class CountryDetailUiState {
    object Loading : CountryDetailUiState()
    data class Success(val data: CountryDetail) : CountryDetailUiState()
    data class Error(val message: String) : CountryDetailUiState()
}