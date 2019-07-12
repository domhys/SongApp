package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_local.LocalSongsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetLocalSongsUseCase(
    private val scheduler: Scheduler,
    private val localSongsRepository: LocalSongsRepository
) {

    fun getSongs(query: String): Single<List<Song>> =
        Single.fromCallable { localSongsRepository.getSongs() }
            .flatMapObservable { songs -> Observable.fromIterable(songs) }
            .filter { song -> song.title.contains(query, ignoreCase = true) }
            .toList()
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
}