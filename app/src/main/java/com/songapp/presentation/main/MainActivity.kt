package com.songapp.presentation.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.songapp.R
import com.songapp.domain.model.Song
import com.songapp.presentation.base.BaseView
import com.songapp.presentation.main.di.DaggerMainComponent
import com.songapp.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import net.idik.lib.slimadapter.SlimAdapter
import javax.inject.Inject

class MainActivity : BaseView<MainContract.Presenter>(), MainContract.View {

    @Inject override lateinit var presenter: MainContract.Presenter
    private val adapter = SlimAdapter.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSongsList()
    }

    override fun createGraph() {
        DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }

    private fun setUpSongsList() {
        songsList.layoutManager = LinearLayoutManager(this)
        adapter.register<Song>(R.layout.songs_list_row) { data, injector ->
            injector.text(R.id.title, data.title)
                .text(R.id.artist, data.artist)
                .text(R.id.releaseYear, data.releaseYear)
        }.attachTo(songsList)
    }

    override fun displaySongs(songs: List<Song>) {
        adapter.updateData(songs)
    }
}
