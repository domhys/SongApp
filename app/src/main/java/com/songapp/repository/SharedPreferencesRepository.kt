package com.songapp.repository

import android.content.SharedPreferences
import com.songapp.domain.repositories.DataSourceRepository

open class SharedPreferencesRepository(
    private val sharedPreferences: SharedPreferences
) : DataSourceRepository{

    override fun getIsLocalDataSet(): Boolean {
        return sharedPreferences.getBoolean(LOCAL_SOURCE_KEY, false)
    }

    override fun setLocalData(turnOn: Boolean) {
        sharedPreferences.edit().putBoolean(LOCAL_SOURCE_KEY, turnOn).apply()
    }

    override fun getIsRemoteDataSet(): Boolean {
        return sharedPreferences.getBoolean(REMOTE_SOURCE_KEY, false)
    }

    override fun setRemoteData(turnOn: Boolean) {
        sharedPreferences.edit().putBoolean(REMOTE_SOURCE_KEY, turnOn).apply()
    }

    companion object {
        const val LOCAL_SOURCE_KEY = "localSourceKey"
        const val REMOTE_SOURCE_KEY = "remoteSourceKey"
    }
}