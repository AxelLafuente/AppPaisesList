package com.example.pruebadesarrollador.presentation.CountryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pruebadesarrollador.domain.model.Country
import com.example.pruebadesarrollador.presentation.components.CountryBottomBar
import com.example.pruebadesarrollador.presentation.components.CountryItem
import com.example.pruebadesarrollador.presentation.components.EmptyCountriesState
import com.example.pruebadesarrollador.presentation.components.SearchBar

@Composable
fun CountryScreen(
    viewModel: CountryViewModel = viewModel(),
    onItemClick: (Country) -> Unit
) {
    val countries = viewModel.filteredCountries
    val query = viewModel.query
    val selectedFilter = viewModel.selectedFilter
    val uiState = viewModel.uiState

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            CountryBottomBar(
                selectedFilter = selectedFilter,
                onFilterSelected = viewModel::onFilterSelected
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBar(
                query = query,
                onQueryChange = viewModel::onQueryChange
            )

            when (uiState) {
                is CountryListUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CountryListUiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = uiState.message,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = viewModel::retryFetchCountries
                            ) {
                                Text("Tentar novamente")
                            }
                        }
                    }
                }

                is CountryListUiState.Success -> {
                    if (countries.isEmpty()) {
                        EmptyCountriesState(
                            selectedFilter = selectedFilter,
                            query = query
                        )
                    } else {
                        LazyColumn {
                            items(
                                items = countries,
                                key = { it.common }
                            ) { country ->
                                CountryItem(
                                    imageUrl = country.flagUrl,
                                    commonName = country.common,
                                    officialName = country.official,
                                    capital = country.capital,
                                    isFavorite = country.isFavorite,
                                    onFavoriteClick = {
                                        viewModel.toggleFavorite(country.common)
                                    },
                                    modifier = Modifier.clickable {
                                        onItemClick(country)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}