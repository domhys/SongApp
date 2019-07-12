package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_remote.RestSongRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetRemoteSongsUseCase(
    private val scheduler: Scheduler,
    private val restSongRepository: RestSongRepository
) {

    fun getSongs(query: String): Single<List<Song>> =
        restSongRepository.getSongs(query)
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
}