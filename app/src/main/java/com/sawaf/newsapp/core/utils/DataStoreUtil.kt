package com.sawaf.newsapp.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Preference Name
const val PREFERENCE_NAME = "MyDataStore"

//Instance of DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

/**
 * Add string data to data Store
 */
suspend fun Context.writeString(key: String, value: String) {
    dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
}

/**
 * Read string from the data store preferences
 */
fun Context.readString(key: String, defaultValue: String): Flow<String> {
    return dataStore.data.map{ pref ->
        pref[stringPreferencesKey(key)] ?: defaultValue
    }
}

/*
suspend fun Context.exampleSave() {
    dataStore.edit {
        it[stringPreferencesKey("userName")] = "Mohamed"
        it[intPreferencesKey("userAge")] = 22
    }
}

fun Context.exampleGetString() : Flow<String> {
    return dataStore.data.map {
        it[stringPreferencesKey("userName")] ?: ""
    }
}

fun Context.exampleGetInt() : Flow<Int> {
    return dataStore.data.map {
        it[intPreferencesKey("userAge")] ?: 0
    }
}*/
