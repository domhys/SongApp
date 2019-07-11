package com.songapp.presentation.main

import com.songapp.domain.use_cases.GetLocalSongsUseCase

class MainModel(
    private val getLocalSongsUseCase: GetLocalSongsUseCase
) {

    fun getSongs() = getLocalSongsUseCase.getSongs()
}