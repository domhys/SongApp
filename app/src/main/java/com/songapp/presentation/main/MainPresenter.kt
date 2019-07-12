package com.songapp.presentation.main

import com.songapp.domain.model.DataSource
import com.songapp.presentation.base.BasePresenter
import timber.log.Timber
import java.lang.IllegalArgumentException

class MainPresenter(
    val mainView: MainContract.View,
    val mainModel: MainModel
) : BasePresenter(), MainContract.Presenter {

    override fun onBind() {
        super.onBind()
        getSongs("")
    }

    private fun getSongs(query: String) {
        register(mainModel.getSongs(query)
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

    override fun settingsClicked() {
        mainView.displayChooseSourceDialog(
            booleanArrayOf(mainModel.isLocalDataTurnedOn, mainModel.isRemoteDataTurnedOn)
        )
    }

    override fun sourceChanged(source: DataSource, isChecked: Boolean) {
        when(source) {
            DataSource.LOCAL -> mainModel.isLocalDataTurnedOn = isChecked
            DataSource.REMOTE -> mainModel.isRemoteDataTurnedOn = isChecked
            DataSource.INVALID -> throw IllegalArgumentException()
        }
    }
}