package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.domain.model.SongFactory
import com.songapp.repository.songs_remote.RestSongRepository
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenDo

class GetRemoteSongsUseCaseTest {

    @Mock lateinit var restSongRepository: RestSongRepository
    private lateinit var getRemoteSongsUseCase: GetRemoteSongsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRemoteSongsUseCase = GetRemoteSongsUseCase(TestScheduler(), restSongRepository)
    }

    @Test
    fun shouldReturnSongs() {
        //given
        val query = "query"
        val songsList: List<Song> = SongFactory.createSongList()
        whenDo(restSongRepository.getSongs(query)).thenReturn(Single.just(songsList))

        //when
        val songs = getRemoteSongsUseCase.withQuery(query).performOnTheSameThread().blockingGet()

        //then
        verify(restSongRepository).getSongs(query)
        assert(songsList.size == songs.size)
        assert(songsList[0] == songs[0])
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowWhenQueryNotProvided() {
        // when
        getRemoteSongsUseCase.performOnTheSameThread().blockingGet()
    }
}