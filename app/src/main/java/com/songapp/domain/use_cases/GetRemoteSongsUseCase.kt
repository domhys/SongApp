package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_remote.RestSongRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetRemoteSongsUseCase(
    private val restSongRepository: RestSongRepository
) {

    fun getSongs(query: String): Single<List<Song>> =
        restSongRepository.getSongs(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}