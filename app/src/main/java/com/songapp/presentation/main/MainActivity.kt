package com.songapp.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.songapp.R
import com.songapp.domain.model.Song
import com.songapp.presentation.base.BaseView
import com.songapp.presentation.main.di.DaggerMainComponent
import com.songapp.presentation.main.di.MainModule
import com.songapp.utility.extensions.setOnQueryChange
import kotlinx.android.synthetic.main.activity_main.*
import net.idik.lib.slimadapter.SlimAdapter
import javax.inject.Inject

class MainActivity : BaseView<MainContract.Presenter>(), MainContract.View {

    @Inject override lateinit var presenter: MainContract.Presenter
    private val adapter = SlimAdapter.create()
    private lateinit var searchView: SearchView

    private var searchDelay: Long = DEFAULT_SEARCH_DELAY_MILLIS

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_search, menu)
        searchView = (menu?.findItem(R.id.actionSearch)?.actionView as SearchView).apply {
            setOnQueryChange(
                searchDelay,
                { presenter.searchQuerySubmitted(it) },
                { presenter.queryTextChanged(it) }
            )
        }
        return true
    }

    override fun displaySongs(songs: List<Song>) {
        adapter.updateData(songs)
    }

    companion object {
        const val DEFAULT_SEARCH_DELAY_MILLIS = 300L
    }
}
