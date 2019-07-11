package com.songapp.application

import android.app.Application
import com.songapp.application.di.ApplicationComponent
import com.songapp.application.di.ApplicationModule
import com.songapp.application.di.DaggerApplicationComponent
import com.songapp.application.di.RepositoryModule

class SongApplication : Application() {

    internal lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = createGraph()
        applicationComponent.inject(this)
    }

    private fun createGraph() =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .build()
}