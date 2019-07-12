package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction

open class GetSongsUseCase(
    scheduler: Scheduler,
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase,
    private val isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
    private val isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase,
    private val query: String? = null
): UseCase<List<Song>>(scheduler) {

    open fun withQuery(query: String) = GetSongsUseCase(
        scheduler,
        getLocalSongsUseCase,
        getRemoteSongsUseCase,
        isLocalDataTurnedOnUseCase,
        isRemoteDataTurnedOnUseCase,
        query
    )

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

    override fun doWork(): Single<List<Song>> {
        if (query == null) throw IllegalArgumentException("please provide query")
        return getLocalSongs(query).zipWith(
            getRemoteSongs(query).onErrorReturn { emptyList() },
            BiFunction { localSongs: List<Song>, remoteSongs: List<Song> -> localSongs + remoteSongs }
        )
    }
}