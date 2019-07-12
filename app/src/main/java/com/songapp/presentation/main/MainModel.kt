package com.songapp.presentation.main

import com.songapp.domain.use_cases.*

open class MainModel(
    private val getSongsUseCase: GetSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {
    open var currentQuery = ""

    open var isLocalDataTurnedOn: Boolean
        get() = isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn()
        set(value) = isLocalDataTurnedOnUseCase.setLocalDataSourceTurnedOn(value)

    open var isRemoteDataTurnedOn: Boolean
        get() = isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn()
        set(value) = isRemoteDataTurnedOnUseCase.setRemoteDataSourceTurnedOn(value)

    open fun getSongs(query: String) = getSongsUseCase.getSongs(query)
}