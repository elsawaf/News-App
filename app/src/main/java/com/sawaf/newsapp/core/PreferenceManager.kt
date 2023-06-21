package com.sawaf.newsapp.core

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_COUNTRY_CODE = "key_code"

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_preferences")

    suspend fun saveCountryCode(value: String) {
        val dataStoreKey = stringPreferencesKey(KEY_COUNTRY_CODE)
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun getCountryCode(): String {
        val dataStoreKey = stringPreferencesKey(KEY_COUNTRY_CODE)
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey] ?: "us"
    }

    suspend fun <T> put(item: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(item)
        //Save that String in SharedPreferences
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = jsonString
        }
    }

    suspend fun <T> get(key: String): String? {
        //We read JSON String which was saved.
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data.map {
            it[dataStoreKey]
        }.first()
    }

    suspend inline fun <reified T> getItem(key: String): T? {
        val value = get<T>(key)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}