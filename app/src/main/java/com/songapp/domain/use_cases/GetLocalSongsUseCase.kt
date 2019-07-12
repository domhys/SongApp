package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_local.LocalSongsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.lang.IllegalArgumentException

open class GetLocalSongsUseCase(
    scheduler: Scheduler,
    private val localSongsRepository: LocalSongsRepository,
    private val query: String? = null
): UseCase<List<Song>>(scheduler) {

    open fun withQuery(query: String) = GetLocalSongsUseCase(scheduler, localSongsRepository, query)

    override fun doWork(): Single<List<Song>> {
        if (query == null) throw IllegalArgumentException("please provide query")
        return Single.fromCallable { localSongsRepository.getSongs() }
            .flatMapObservable { songs -> Observable.fromIterable(songs) }
            .filter { song -> song.title.contains(query, ignoreCase = true) }
            .toList()
    }
}