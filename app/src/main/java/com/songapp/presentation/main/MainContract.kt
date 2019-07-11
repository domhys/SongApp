package com.songapp.presentation.main

import com.songapp.domain.model.Song
import com.songapp.presentation.IBasePresenter
import com.songapp.presentation.IBaseView

interface MainContract {

    interface View : IBaseView<Presenter> {
        fun displaySongs(songs: List<Song>)
    }

    interface Presenter: IBasePresenter {

    }
}