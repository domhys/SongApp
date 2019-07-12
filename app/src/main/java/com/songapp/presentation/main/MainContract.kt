package com.songapp.presentation.main

import com.songapp.domain.model.DataSource
import com.songapp.domain.model.Song
import com.songapp.presentation.IBasePresenter
import com.songapp.presentation.IBaseView

interface MainContract {

    interface View : IBaseView<Presenter> {
        fun displaySongs(songs: List<Song>)
        fun displayChooseSourceDialog(dataSourcesState: BooleanArray)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter: IBasePresenter {
        fun searchQuerySubmitted(query: String): Boolean
        fun queryTextChanged(query: String): Boolean
        fun settingsClicked()
        fun sourceChanged(source: DataSource, isChecked: Boolean)
        fun onSourceDialogPositiveButtonClicked()
    }
}