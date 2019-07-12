package com.songapp.presentation.base

import androidx.annotation.VisibleForTesting
import com.songapp.presentation.IBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter : IBasePresenter {

    val compositeDisposable = CompositeDisposable()
        @VisibleForTesting get() = field

    override fun onBind() {
        /* NO-OP */
    }

    fun register(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}