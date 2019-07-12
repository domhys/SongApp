package com.songapp.presentation.base

import io.reactivex.Observable
import org.junit.Before
import org.junit.Test


class BasePresenterTest {

    private lateinit var basePresenter: BasePresenter

    @Before
    fun setUp() {
        basePresenter = BasePresenter()
    }

    @Test
    fun registerDisposable() {
        //given
        assert(basePresenter.compositeDisposable.size() == 0)

        //when
        basePresenter.register(Observable.just(Any()).subscribe())

        //then
        assert(basePresenter.compositeDisposable.size() == 1)
    }

    @Test
    fun dispose() {
        //given
        assert(!basePresenter.compositeDisposable.isDisposed)

        //when
        basePresenter.onDestroy()

        //then
        assert(basePresenter.compositeDisposable.isDisposed)
    }
}