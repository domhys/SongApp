package com.songapp.presentation.main

import com.songapp.domain.use_cases.GetLocalSongsUseCase
import com.songapp.domain.use_cases.GetRemoteSongsUseCase

class MainModel(
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase
) {

    fun getLocalSongs(query: String) = getLocalSongsUseCase.getSongs(query)

    fun getRemoteSongs(query: String) = getRemoteSongsUseCase.getSongs(query)
}