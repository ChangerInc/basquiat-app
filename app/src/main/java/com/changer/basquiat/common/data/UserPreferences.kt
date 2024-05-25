package com.changer.basquiat.common.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.changer.basquiat.ui.login.domain.UsuarioToken
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
}