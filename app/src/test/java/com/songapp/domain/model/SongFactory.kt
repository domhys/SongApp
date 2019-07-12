package com.songapp.domain.model

object SongFactory {

    fun createSong() = Song(title = "title", artist = "artist", releaseYear = "1994")

    fun createSongList() = listOf(createSong())
}