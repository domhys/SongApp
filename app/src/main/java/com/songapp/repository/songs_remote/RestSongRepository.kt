package com.songapp.repository.songs_remote

import com.songapp.domain.model.Song
import com.songapp.domain.repositories.SongRepository
import io.reactivex.Observable
import io.reactivex.Single

open class RestSongRepository(
    private val songService: SongService
) :SongRepository {

    override fun getSongs(query: String): Single<List<Song>> {
        return songService.getSongs(query, type = "song")
            .flatMapObservable { Observable.fromIterable(it.results) }
            .map(::Song)
            .toList()
    }
}