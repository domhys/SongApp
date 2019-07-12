package com.songapp.domain.model

import com.google.gson.annotations.SerializedName

data class Song(
    val title: String,
    val artist: String,
    val releaseYear: String
) {
    constructor(songLocalEntity: SongLocalEntity) : this(songLocalEntity.title, songLocalEntity.artist, songLocalEntity.releaseYear)

    constructor(songRemoteEntity: SongRemoteEntity) : this(songRemoteEntity.title, songRemoteEntity.artist, NO_DATA)
}

data class SongLocalEntity(
    @SerializedName("Song Clean") val title: String = NO_DATA,
    @SerializedName("ARTIST CLEAN") val artist: String = NO_DATA,
    @SerializedName("Release Year") val releaseYear: String = NO_DATA
)

data class SongsRemoteEntity(
    @SerializedName("results") val results: List<SongRemoteEntity>
)

data class SongRemoteEntity(
    @SerializedName("trackName") val title: String = NO_DATA,
    @SerializedName("artistName") val artist: String = NO_DATA //release year is not in the api return entity
)

private const val NO_DATA = ""