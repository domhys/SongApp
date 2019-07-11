package com.songapp.domain.use_cases

import com.songapp.domain.repositories.DataSourceRepository

class IsLocalDataTurnedOnUseCase(
    private val dataSourceRepository: DataSourceRepository
) {

    fun getIsLocalDataSourceTurnedOn(): Boolean {
        return dataSourceRepository.getIsLocalDataSet()
    }

    fun setLocalDataSourceTurnedOn(turnOn: Boolean) {
        dataSourceRepository.setLocalData(turnOn)
    }
}