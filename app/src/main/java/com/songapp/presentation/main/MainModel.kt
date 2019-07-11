package com.songapp.presentation.main

import com.songapp.domain.model.Song
import com.songapp.repository.songs_local.LocalSongsRepository
import io.reactivex.Single

class MainModel(
    private val localSongsRepository: LocalSongsRepository
) {

    fun getSongs(): Single<List<Song>> =
        Single.fromCallable { localSongsRepository.getSongs() }
}