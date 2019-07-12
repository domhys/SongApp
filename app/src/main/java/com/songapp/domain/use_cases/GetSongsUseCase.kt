package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetSongsUseCase(
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
) {

    private fun getLocalSongs(query: String): Single<List<Song>> {
        return if (isLocalDataTurnedOnUseCase.getIsLocalDataSourceTurnedOn())
            getLocalSongsUseCase.getSongs(query)
        else Single.just(emptyList())
    }

    private fun getRemoteSongs(query: String): Single<List<Song>> {
        return if (isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn())
            getRemoteSongsUseCase.getSongs(query)
        else Single.just(emptyList())
    }

    fun getSongs(query: String): Single<List<Song>> {
        return getLocalSongs(query).zipWith(
            getRemoteSongs(query),
            BiFunction { localSongs, remoteSongs -> localSongs + remoteSongs }
        )
    }
}