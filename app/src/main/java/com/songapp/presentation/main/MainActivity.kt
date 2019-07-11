package com.songapp.presentation.main

import android.os.Bundle
import com.songapp.R
import com.songapp.presentation.base.BaseView
import com.songapp.presentation.main.di.DaggerMainComponent
import com.songapp.presentation.main.di.MainModule
import javax.inject.Inject

class MainActivity : BaseView<MainContract.Presenter>(), MainContract.View {

    @Inject override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createGraph() {
        DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }
}
