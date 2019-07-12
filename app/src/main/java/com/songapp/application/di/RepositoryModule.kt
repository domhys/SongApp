package com.songapp.application.di

import android.content.SharedPreferences
import com.songapp.repository.SharedPreferencesRepository
import com.songapp.repository.songs_remote.RestSongRepository
import com.songapp.repository.songs_remote.SongService
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun providesRestSongRepository(songService: SongService): RestSongRepository {
        return RestSongRepository(songService)
    }

    @Provides
    fun providesSharedPreferencesRepository(sharedPreferences: SharedPreferences): SharedPreferencesRepository {
        return SharedPreferencesRepository(sharedPreferences)
    }
}