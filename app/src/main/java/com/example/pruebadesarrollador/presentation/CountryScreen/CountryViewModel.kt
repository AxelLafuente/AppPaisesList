package com.example.pruebadesarrollador.presentation.CountryScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebadesarrollador.data.local.FavoritesPreferences
import com.example.pruebadesarrollador.data.remote.api.RetrofitInstance
import com.example.pruebadesarrollador.domain.model.Country
import com.example.pruebadesarrollador.domain.model.toDomain
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class CountryViewModel(application: Application) : AndroidViewModel(application) {

    var uiState by mutableStateOf<CountryListUiState>(CountryListUiState.Loading)
        private set

    private val favoritesPreferences = FavoritesPreferences(application)

    private var allCountries: List<Country> by mutableStateOf(emptyList())

    private var favoriteNames: Set<String> by mutableStateOf(emptySet())

    var selectedFilter by mutableStateOf(CountryListFilter.ALL)
        private set

    var query by mutableStateOf("")
        private set

    fun onFilterSelected(filter: CountryListFilter) {
        selectedFilter = filter
    }

    val filteredCountries: List<Country>
        get() {
            val countriesWithFavorites = allCountries.map { country ->
                country.copy(isFavorite = favoriteNames.contains(country.common))
            }

            val baseList = when (selectedFilter) {
                CountryListFilter.ALL -> countriesWithFavorites
                CountryListFilter.FAVORITES -> countriesWithFavorites.filter { it.isFavorite }
            }

            return if (query.isBlank()) {
                when (selectedFilter) {
                    CountryListFilter.ALL -> {
                        baseList
                            .sortedBy { it.common.lowercase() }
                            .take(10)
                    }

                    CountryListFilter.FAVORITES -> {
                        baseList
                            .sortedBy { it.common.lowercase() }
                    }
                }
            } else {
                when (selectedFilter) {
                    CountryListFilter.ALL -> {
                        baseList
                            .filter {
                                it.common.contains(query, ignoreCase = true)
                            }
                            .sortedWith(
                                compareBy<Country> {
                                    !it.common.startsWith(query, ignoreCase = true)
                                }.thenBy {
                                    it.common.lowercase()
                                }
                            )
                    }

                    CountryListFilter.FAVORITES -> {
                        baseList
                            .filter {
                                it.common.contains(query, ignoreCase = true)
                            }
                            .sortedWith(
                                compareBy<Country> {
                                    !it.common.startsWith(query, ignoreCase = true)
                                }.thenBy {
                                    it.common.lowercase()
                                }
                            )
                    }
                }
            }
        }

    init {
        observeFavorites()
        fetchCountries()
    }

    fun onQueryChange(newQuery: String) {
        query = newQuery
    }

    fun toggleFavorite(countryName: String) {
        viewModelScope.launch {
            favoritesPreferences.toggleFavorite(countryName)
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoritesPreferences.favoritesFlow.collectLatest { favorites ->
                favoriteNames = favorites
            }
        }
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            uiState = CountryListUiState.Loading

            try {
                val response = RetrofitInstance.api.getCountries()
                allCountries = response.map { it.toDomain() }
                uiState = CountryListUiState.Success(allCountries)
            } catch (e: IOException) {
                uiState = CountryListUiState.Error("No internet connection.")
            } catch (e: HttpException) {
                uiState = CountryListUiState.Error("Server error: ${e.code()}")
            } catch (e: Exception) {
                uiState = CountryListUiState.Error("Unexpected error.")
            }
        }
    }
    fun retryFetchCountries() {
        fetchCountries()
    }
}