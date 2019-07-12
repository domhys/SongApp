package com.songapp.repository.songs_remote

import com.songapp.domain.model.SongRemoteEntity
import com.songapp.domain.model.SongsRemoteEntity
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.Mockito.`when` as whenDo

class RestSongRepositoryTest {

    @get:Rule val rule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks private lateinit var restSongRepository: RestSongRepository

    @Mock private lateinit var songService: SongService

    @Test
    fun shouldReturnAndTransformSongs() {
        //given
        val query = "query"
        val songs = SongsRemoteEntity(results = listOf(SongRemoteEntity("title", "artist")))
        whenDo(songService.getSongs(anyString(), anyString())).thenReturn(Single.just(songs))

        //when
        val songsGotten = restSongRepository.getSongs(query).blockingGet()

        //then
        assert(songsGotten.size == 1)
        assert(songsGotten[0].title == songs.results[0].title)
        assert(songsGotten[0].artist == songs.results[0].artist)
        assert(songsGotten[0].releaseYear == "")
    }
}