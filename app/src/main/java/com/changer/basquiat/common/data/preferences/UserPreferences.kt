package com.changer.basquiat.common.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.changer.basquiat.domain.model.UsuarioToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val gson = Gson()
    }

    val authToken: Flow<UsuarioToken?>
        get() = context.dataStore.data
            .map { preferences ->
                preferences[TOKEN_KEY]?.let { json ->
                    gson.fromJson(json, UsuarioToken::class.java)
                }
            }

    suspend fun saveAuthToken(token: UsuarioToken) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = gson.toJson(token)
        }
    }

    suspend fun updateProfilePhoto(newPhotoUrl: String) {
        context.dataStore.edit { preferences ->
            val currentTokenJson = preferences[TOKEN_KEY]
            if (currentTokenJson != null) {
                val currentToken = gson.fromJson(currentTokenJson, UsuarioToken::class.java)
                val updatedToken = currentToken.copy(fotoPerfil = newPhotoUrl)
                preferences[TOKEN_KEY] = gson.toJson(updatedToken)
            }
        }
    }

    suspend fun clear() {
    context.dataStore.edit { preferences ->
        preferences.clear()
    }
}
}