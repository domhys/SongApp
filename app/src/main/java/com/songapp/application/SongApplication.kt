package com.songapp.application

import android.app.Application
import com.songapp.BuildConfig
import com.songapp.application.di.ApplicationComponent
import com.songapp.application.di.ApplicationModule
import com.songapp.application.di.DaggerApplicationComponent
import com.songapp.application.di.RepositoryModule
import timber.log.Timber

class SongApplication : Application() {

    internal lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initTimber()
        applicationComponent = createGraph()
        applicationComponent.inject(this)
    }

    private fun createGraph() =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .build()

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}