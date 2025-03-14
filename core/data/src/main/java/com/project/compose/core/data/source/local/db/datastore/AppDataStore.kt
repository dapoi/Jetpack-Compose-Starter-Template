package com.project.compose.core.data.source.local.db.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "app_data_store")

class AppDataStore @Inject constructor(context: Context) {

    private val dataStore = context.dataStore
}