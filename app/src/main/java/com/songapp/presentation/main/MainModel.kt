package com.songapp.presentation.main

import com.songapp.domain.use_cases.*

class MainModel(
    private val getSongsUseCase: GetSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {
    var isLocalDataTurnedOn: Boolean
        get() = isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn()
        set(value) = isLocalDataTurnedOnUseCase.setLocalDataSourceTurnedOn(value)

    var isRemoteDataTurnedOn: Boolean
        get() = isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn()
        set(value) = isRemoteDataTurnedOnUseCase.setRemoteDataSourceTurnedOn(value)

    fun getSongs(query: String) = getSongsUseCase.getSongs(query)
}