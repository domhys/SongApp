package com.songapp.repository.songs_remote

import com.songapp.domain.model.SongsRemoteEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SongService {

    @GET("search")
    fun getSongs(
        @Query("term") query: String,
        @Query("entity") type: String
    ): Single<SongsRemoteEntity>
}