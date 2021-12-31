package com.example.pharmacyapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.pharmacyapp.util.Constant.Companion.PREFERENCE_NAME
import com.example.pharmacyapp.util.Constant.Companion.PREFERENCE_USER_ID
import com.example.pharmacyapp.util.Constant.Companion.PREFERENCE_USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.datastore by preferencesDataStore(PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val userIdKey = intPreferencesKey(PREFERENCE_USER_ID)
        val userTokenKey = stringPreferencesKey(PREFERENCE_USER_TOKEN)
    }

    private val dataStore: DataStore<Preferences> = context.datastore

    suspend fun saveUserData(
        userID: Int,
        userToken: String,
    ): Boolean {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.userIdKey] = userID
            preferences[PreferenceKeys.userTokenKey] = userToken
        }
        return true
    }

    val readUserData: Flow<UserDataStore> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val userId = preferences[PreferenceKeys.userIdKey] ?: 0
            val userToken = preferences[PreferenceKeys.userTokenKey] ?: "none"
            UserDataStore(
                userId,
                userToken
            )
        }
}

data class UserDataStore(
    val userId: Int,
    val userToken: String,
)