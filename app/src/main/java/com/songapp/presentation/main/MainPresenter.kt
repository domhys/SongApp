package com.songapp.presentation.main

import com.songapp.presentation.base.BasePresenter
import timber.log.Timber

class MainPresenter(
    val mainView: MainContract.View,
    val mainModel: MainModel
) : BasePresenter(), MainContract.Presenter {

    override fun onBind() {
        super.onBind()
        getSongs("")
    }

    private fun getSongs(query: String) {
//        register(mainModel.getLocalSongs(query)
//            .subscribe({ songs ->
//                mainView.displaySongs(songs)
//            }, Timber::e))
        register(mainModel.getRemoteSongs(query)
            .subscribe({ songs ->
                mainView.displaySongs(songs)
            }, Timber::e)
        )
    }

    override fun searchQuerySubmitted(query: String): Boolean {
        getSongs(query)
        return true
    }

    override fun queryTextChanged(query: String): Boolean {
        getSongs(query)
        return true
    }
}