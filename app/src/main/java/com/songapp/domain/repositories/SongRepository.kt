package com.songapp.domain.repositories

import com.songapp.domain.model.Song
import io.reactivex.Single

interface SongRepository {
    fun getSongs(query: String): Single<List<Song>>
}