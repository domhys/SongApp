package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class GetSongsUseCase(
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {

    private fun getLocalSongs(query: String): Observable<List<Song>> {
        return if (isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn())
            getLocalSongsUseCase.getSongs(query).toObservable()
        else Observable.empty()
    }

    private fun getRemoteSongs(query: String): Observable<List<Song>> {
        return if (isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn())
            getRemoteSongsUseCase.getSongs(query).toObservable()
        else Observable.empty()
    }

    fun getSongs(query: String): Observable<List<Song>> {
        return getLocalSongs(query).zipWith(
            getRemoteSongs(query),
            BiFunction { localSongs, remoteSongs -> localSongs + remoteSongs }
        )
    }
}