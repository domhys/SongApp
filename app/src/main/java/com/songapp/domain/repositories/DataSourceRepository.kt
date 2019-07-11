package com.songapp.domain.repositories

interface DataSourceRepository {
    fun getIsLocalDataSet(): Boolean
    fun setLocalData(turnOn: Boolean)
    fun getIsRemoteDataSet(): Boolean
    fun setRemoteData(turnOn: Boolean)
}