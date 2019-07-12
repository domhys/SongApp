package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction

open class GetSongsUseCase(
    private val scheduler: Scheduler,
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {

    private fun getLocalSongs(query: String): Single<List<Song>> {
        return if (isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn())
            getLocalSongsUseCase.withQuery(query).perform()
        else Single.just(emptyList())
    }

    private fun getRemoteSongs(query: String): Single<List<Song>> {
        return if (isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn())
            getRemoteSongsUseCase.withQuery(query).perform()
        else Single.just(emptyList())
    }

    open fun getSongs(query: String): Single<List<Song>> {
        return getLocalSongs(query).zipWith(
            getRemoteSongs(query).onErrorReturn { emptyList() },
            BiFunction { localSongs: List<Song>, remoteSongs: List<Song> -> localSongs + remoteSongs }
        )
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
    }
}