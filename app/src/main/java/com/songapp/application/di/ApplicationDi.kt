package com.songapp.application.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.songapp.application.SongApplication
import com.songapp.repository.SharedPreferencesRepository
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
    fun providesSharedPreferencesRepository(): SharedPreferencesRepository
}

@Module
open class ApplicationModule(val application: SongApplication) {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun providesSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    companion object {
        const val SHARED_PREFERENCES_FILE = "prefs"
    }
}