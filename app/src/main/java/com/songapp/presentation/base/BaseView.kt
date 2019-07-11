package com.songapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.songapp.presentation.IBasePresenter
import com.songapp.presentation.IBaseView

abstract class BaseView<T : IBasePresenter> : AppCompatActivity(), IBaseView<T> {

    abstract override val presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createGraph()
    }

    abstract fun createGraph()

    override fun onStart() {
        super.onStart()
        presenter.onBind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}