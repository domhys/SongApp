package com.songapp.presentation.base

import com.songapp.presentation.IBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter: IBasePresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onBind() {
        /* NO-OP */
    }

    fun register(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}