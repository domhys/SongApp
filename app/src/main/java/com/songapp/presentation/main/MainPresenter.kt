package com.songapp.presentation.main

import com.songapp.domain.model.DataSource
import com.songapp.presentation.base.BasePresenter
import timber.log.Timber
import java.lang.IllegalArgumentException

open class MainPresenter(
    private val mainView: MainContract.View,
    private val mainModel: MainModel
) : BasePresenter(), MainContract.Presenter {

    override fun onBind() {
        super.onBind()
        getSongs()
    }

    private fun getSongs() {
        register(mainModel.getSongs(mainModel.currentQuery)
            .doOnSubscribe { mainView.showProgressBar() }
            .doFinally { mainView.hideProgressBar() }
            .subscribe({ songs ->
                mainView.displaySongs(songs)
            }, Timber::e)
        )
    }

    override fun searchQuerySubmitted(query: String) = queryChanged(query)

    override fun queryTextChanged(query: String) = queryChanged(query)

    private fun queryChanged(query: String): Boolean {
        mainModel.currentQuery = query
        getSongs()
        return true
    }

    override fun settingsClicked() {
        mainView.displayChooseSourceDialog(
            booleanArrayOf(mainModel.isLocalDataTurnedOn, mainModel.isRemoteDataTurnedOn)
        )
    }

    override fun onSourceDialogPositiveButtonClicked() {
        getSongs()
    }

    override fun sourceChanged(source: DataSource, isChecked: Boolean) {
        when (source) {
            DataSource.LOCAL -> mainModel.isLocalDataTurnedOn = isChecked
            DataSource.REMOTE -> mainModel.isRemoteDataTurnedOn = isChecked
            DataSource.INVALID -> throw IllegalArgumentException()
        }
    }
}