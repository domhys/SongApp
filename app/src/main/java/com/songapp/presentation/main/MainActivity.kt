package com.songapp.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.songapp.R
import com.songapp.application.SongApplication
import com.songapp.domain.model.DataSource
import com.songapp.domain.model.Song
import com.songapp.presentation.base.BaseView
import com.songapp.presentation.main.di.DaggerMainComponent
import com.songapp.presentation.main.di.MainModule
import com.songapp.utility.extensions.setOnQueryChange
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar_overlay.*
import net.idik.lib.slimadapter.SlimAdapter
import javax.inject.Inject

open class MainActivity : BaseView<MainContract.Presenter>(), MainContract.View {

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
            .applicationComponent((application as SongApplication).applicationComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }

    private fun setUpSongsList() {
        songsList.layoutManager = LinearLayoutManager(this)
        addListDividers()
        adapter.register<Song>(R.layout.songs_list_row) { data, injector ->
            injector.text(R.id.title, data.title)
                .text(R.id.artist, data.artist)
                .text(R.id.releaseYear, data.releaseYear)
                .visibility(R.id.releaseYear, if (data.releaseYear.isEmpty()) View.GONE else View.VISIBLE )
        }.attachTo(songsList)
    }

    private fun addListDividers() {
        songsList.addItemDecoration(
            DividerItemDecoration(songsList.context, (songsList.layoutManager as LinearLayoutManager).orientation))
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings) {
            presenter.settingsClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun displaySongs(songs: List<Song>) {
        if (songs.isEmpty()) displayNoResultsView() else displaySongs()
        adapter.updateData(songs)
    }

    private fun displayNoResultsView() {
        emptyView.isVisible = true
        songsList.isGone = true
    }

    private fun displaySongs() {
        emptyView.isGone = true
        songsList.isVisible = true
    }

    override fun showProgressBar() {
        progressBarLayout.isVisible = true
    }

    override fun hideProgressBar() {
        progressBarLayout.isGone = true
    }

    override fun displayChooseSourceDialog(dataSourcesState: BooleanArray) {
        AlertDialog.Builder(this)
            .setMultiChoiceItems(R.array.songs_source_options, dataSourcesState
            ) { _, which, isChecked ->
                presenter.sourceChanged(DataSource.createFromIndex(which), isChecked)

            }.setTitle(R.string.choose_source)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                presenter.onSourceDialogPositiveButtonClicked()
                dialog.dismiss()
            }.create()
            .show()
    }

    companion object {
        const val DEFAULT_SEARCH_DELAY_MILLIS = 300L
    }
}
