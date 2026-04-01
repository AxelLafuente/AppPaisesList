package com.example.pruebadesarrollador.presentation.CountryDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebadesarrollador.data.remote.api.RetrofitInstance
import com.example.pruebadesarrollador.data.remote.mapper.toDomain
import kotlinx.coroutines.launch

class CountryDetailViewModel : ViewModel() {

    var uiState by mutableStateOf<CountryDetailUiState>(CountryDetailUiState.Loading)
        private set

    fun fetchCountry(name: String) {
        viewModelScope.launch {
            uiState = CountryDetailUiState.Loading

            try {
                val response = RetrofitInstance.api.getCountryByName(name)

                val country = response
                    .firstOrNull { it.name.common.equals(name, ignoreCase = true) }
                    ?.toDomain()

                if (country != null) {
                    uiState = CountryDetailUiState.Success(country)
                } else {
                    uiState = CountryDetailUiState.Error("País não encontrado")
                }

            } catch (e: Exception) {
                uiState = CountryDetailUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}