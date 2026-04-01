package com.example.pruebadesarrollador.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favorites_prefs")

class FavoritesPreferences(private val context: Context) {

    private companion object {
        val FAVORITE_COUNTRIES_KEY = stringSetPreferencesKey("favorite_countries")
    }

    val favoritesFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[FAVORITE_COUNTRIES_KEY] ?: emptySet()
    }

    suspend fun toggleFavorite(countryName: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITE_COUNTRIES_KEY]?.toMutableSet() ?: mutableSetOf()

            if (current.contains(countryName)) {
                current.remove(countryName)
            } else {
                current.add(countryName)
            }

            preferences[FAVORITE_COUNTRIES_KEY] = current
        }
    }
}