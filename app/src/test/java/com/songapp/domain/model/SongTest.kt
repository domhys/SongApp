package com.songapp.domain.model

import org.junit.Test


class SongTest {

    @Test
    fun shouldCreateSongFromSongLocalEntity() {
        //given
        val songLocalEntity = SongLocalEntity(title = "title", artist = "artist", releaseYear = "1994")

        // when
        val song = Song(songLocalEntity)

        // then
        assert(song.title == songLocalEntity.title)
        assert(song.artist == songLocalEntity.artist)
        assert(song.releaseYear == songLocalEntity.releaseYear)
    }
}