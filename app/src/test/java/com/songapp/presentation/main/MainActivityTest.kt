package com.songapp.presentation.main

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.songapp.domain.model.Song
import com.songapp.domain.model.SongFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    lateinit var activity: MainActivity
    private val songs = SongFactory.createSongList()

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
        activity.adapter.updateData(emptyList<Song>())
    }

    @Test
    fun shouldDisplaySongs() {
        //when
        activity.displaySongs(songs)

        //then
        assert(activity.adapter.data.size == songs.size)
    }

    @Test
    fun shouldShowListAndHideEmpty() {
        //when
        activity.displaySongs(songs)

        //then
        assert(activity.emptyView.isGone)
        assert(activity.songsList.isVisible)
    }

    @Test
    fun shouldShowEmptyAndHideList() {
        //when
        activity.displaySongs(emptyList())

        //then
        assert(activity.emptyView.isVisible)
        assert(activity.songsList.isGone)
    }
}