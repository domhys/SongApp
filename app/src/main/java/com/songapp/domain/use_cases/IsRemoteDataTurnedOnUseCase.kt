package com.songapp.domain.use_cases

import com.songapp.domain.repositories.DataSourceRepository

open class IsRemoteDataTurnedOnUseCase(
    private val dataSourceRepository: DataSourceRepository
) {

    open fun getIsRemoteDataSourceTurnedOn(): Boolean {
        return dataSourceRepository.getIsRemoteDataSet()
    }

    open fun setRemoteDataSourceTurnedOn(turnOn: Boolean) {
        dataSourceRepository.setRemoteData(turnOn)
    }
}