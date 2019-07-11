package com.songapp.presentation.main

import com.songapp.presentation.base.BasePresenter
import timber.log.Timber

class MainPresenter(
    val mainView: MainContract.View,
    val mainModel: MainModel
) : BasePresenter(), MainContract.Presenter {

    override fun onBind() {
        super.onBind()
        getSongs()
    }

    private fun getSongs() {
        register(mainModel.getSongs()
            .subscribe({ songs ->
                mainView.displaySongs(songs)
            }, Timber::e))
    }
}