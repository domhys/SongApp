package com.songapp.application.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.songapp.application.SongApplication
import com.songapp.repository.songs_remote.RestSongRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class, ApplicationModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun inject(application: Application)
    fun provideGson(): Gson
    fun provideRestSongRepository(): RestSongRepository
}

@Module
class ApplicationModule(application: SongApplication) {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }
}