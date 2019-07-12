package com.songapp.domain.use_cases

import com.songapp.domain.repositories.DataSourceRepository

open class IsLocalDataTurnedOnUseCase(
    private val dataSourceRepository: DataSourceRepository
) {

    open fun getIsLocalDataSourceTurnedOn(): Boolean {
        return dataSourceRepository.getIsLocalDataSet()
    }

    open fun setLocalDataSourceTurnedOn(turnOn: Boolean) {
        dataSourceRepository.setLocalData(turnOn)
    }
}