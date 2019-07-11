package com.songapp.presentation.main

import com.songapp.presentation.IBasePresenter
import com.songapp.presentation.IBaseView

interface MainContract {

    interface View : IBaseView<Presenter> {

    }

    interface Presenter: IBasePresenter {

    }
}