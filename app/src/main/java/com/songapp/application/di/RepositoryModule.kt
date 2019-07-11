package com.songapp.application.di

import com.songapp.repository.songs_remote.RestSongRepository
import com.songapp.repository.songs_remote.SongService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesRestSongRepository(songService: SongService): RestSongRepository {
        return RestSongRepository(songService)
    }
}