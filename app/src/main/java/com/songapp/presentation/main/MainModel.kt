package com.songapp.presentation.main

import com.songapp.domain.use_cases.IsLocalDataTurnedOnUseCase
import com.songapp.domain.use_cases.IsRemoteDataTurnedOnUseCase
import com.songapp.domain.use_cases.GetLocalSongsUseCase
import com.songapp.domain.use_cases.GetRemoteSongsUseCase

class MainModel(
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {
    var isLocalDataTurnedOn: Boolean
        get() = isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn()
        set(value) = isLocalDataTurnedOnUseCase.setLocalDataSourceTurnedOn(value)

    var isRemoteDataTurnedOn: Boolean
        get() = isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn()
        set(value) = isRemoteDataTurnedOnUseCase.setRemoteDataSourceTurnedOn(value)

    fun getLocalSongs(query: String) = getLocalSongsUseCase.getSongs(query)

    fun getRemoteSongs(query: String) = getRemoteSongsUseCase.getSongs(query)
}