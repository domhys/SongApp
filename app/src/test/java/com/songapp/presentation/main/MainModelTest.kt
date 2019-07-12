package com.songapp.presentation.main

import com.songapp.domain.model.Song
import com.songapp.domain.model.SongFactory
import com.songapp.domain.use_cases.GetSongsUseCase
import com.songapp.domain.use_cases.IsLocalDataTurnedOnUseCase
import com.songapp.domain.use_cases.IsRemoteDataTurnedOnUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.Mockito.`when` as whenDo


class MainModelTest {

    @get:Rule val rule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks private lateinit var model: MainModel
    @Mock private lateinit var getSongsUseCase: GetSongsUseCase
    @Mock private lateinit var isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase
    @Mock private lateinit var isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase

    @Before
    fun setUp() {
        whenDo(getSongsUseCase.withQuery(anyString())).thenReturn(getSongsUseCase)
    }

    @Test
    fun shouldReturnSongs() {
        //given
        val query = "query"
        val songsList = SongFactory.createSongList()
        whenDo(getSongsUseCase.perform()).thenReturn(Single.just(songsList))

        // when
        val songs = model.getSongs(query).blockingGet()

        // then
        verify(getSongsUseCase).withQuery(query)
        assert(songs.size == songsList.size)
        assert(songs[0] == songsList[0])
    }

    @Test
    fun shouldReturnRemoteDataTurnedOn() {
        //given
        whenDo(isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn()).thenReturn(true)

        //when
        val isRemoteTurnedOn = model.isRemoteDataTurnedOn

        //then
        assert(isRemoteTurnedOn)

        //given
        whenDo(isRemoteDataTurnedOnUseCase.getIsRemoteDataSourceTurnedOn()).thenReturn(false)

        //when
        val isRemoteTurnedOnAfter = model.isRemoteDataTurnedOn

        //then
        assert(!isRemoteTurnedOnAfter)
    }
}