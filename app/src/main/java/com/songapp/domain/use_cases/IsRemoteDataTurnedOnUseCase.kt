package com.songapp.domain.use_cases

import com.songapp.domain.repositories.DataSourceRepository

class IsRemoteDataTurnedOnUseCase(
    private val dataSourceRepository: DataSourceRepository
) {

    fun getIsRemoteDataSourceTurnedOn(): Boolean {
        return dataSourceRepository.getIsRemoteDataSet()
    }

    fun setRemoteDataSourceTurnedOn(turnOn: Boolean) {
        dataSourceRepository.setRemoteData(turnOn)
    }
}