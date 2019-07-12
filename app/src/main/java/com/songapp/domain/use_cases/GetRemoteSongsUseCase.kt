package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_remote.RestSongRepository
import io.reactivex.Scheduler
import io.reactivex.Single

open class GetRemoteSongsUseCase(
    scheduler: Scheduler,
    private val restSongRepository: RestSongRepository,
    private val query: String? = null
): UseCase<List<Song>>(scheduler) {

    open fun withQuery(query: String) = GetRemoteSongsUseCase(scheduler, restSongRepository, query)

    override fun doWork(): Single<List<Song>> {
        if (query == null) throw IllegalArgumentException("please provide query")
        return restSongRepository.getSongs(query)
    }
}