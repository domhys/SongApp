package com.songapp.presentation.main.di

import com.google.gson.Gson
import com.songapp.application.di.ApplicationComponent
import com.songapp.domain.use_cases.*
import com.songapp.presentation.main.MainActivity
import com.songapp.presentation.main.MainContract
import com.songapp.presentation.main.MainModel
import com.songapp.presentation.main.MainPresenter
import com.songapp.repository.SharedPreferencesRepository
import com.songapp.repository.songs_local.LocalSongsRepository
import com.songapp.repository.songs_remote.RestSongRepository
import com.songapp.utility.AssetsStringReader
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Scope

@MainScope
@Component(modules = [MainModule::class], dependencies = [ApplicationComponent::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MainScope

@Module
class MainModule(private val activity: MainActivity) {

    @MainScope
    @Provides
    fun providesView(): MainContract.View = activity

    @MainScope
    @Provides
    fun providesPresenter(
        mainView: MainContract.View,
        mainModel: MainModel
    ): MainContract.Presenter = MainPresenter(mainView, mainModel)

    @MainScope
    @Provides
    fun providesModel(
        getSongsUseCase: GetSongsUseCase,
        isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
        isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
    ): MainModel = MainModel(
        getSongsUseCase,
        isLocalDataTurnedOnUseCase,
        isRemoteDataTurnedOnUseCase
    )

    @MainScope
    @Provides
    fun providesGetLocalSongsUseCase(
        scheduler: Scheduler,
        localSongsRepository: LocalSongsRepository
    ): GetLocalSongsUseCase = GetLocalSongsUseCase(scheduler, localSongsRepository)

    @MainScope
    @Provides
    fun providesGetSongsUseCase(
        scheduler: Scheduler,
        getLocalSongsUseCase: GetLocalSongsUseCase,
        getRemoteSongsUseCase: GetRemoteSongsUseCase,
        isLocalDataTurnedOnUseCase: IsLocalDataTurnedOnUseCase,
        isRemoteDataTurnedOnUseCase: IsRemoteDataTurnedOnUseCase
    ): GetSongsUseCase = GetSongsUseCase(
        scheduler,
        getLocalSongsUseCase,
        getRemoteSongsUseCase,
        isLocalDataTurnedOnUseCase,
        isRemoteDataTurnedOnUseCase
    )

    @MainScope
    @Provides
    fun providesGetRemoteSongsUseCase(
        scheduler: Scheduler,
        restSongRepository: RestSongRepository
    ): GetRemoteSongsUseCase = GetRemoteSongsUseCase(scheduler, restSongRepository)

    @MainScope
    @Provides
    fun providesScheduler(): Scheduler = Schedulers.io()

    @MainScope
    @Provides
    fun providesGetIsLocalDataTurnedOnUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): IsLocalDataTurnedOnUseCase = IsLocalDataTurnedOnUseCase(sharedPreferencesRepository)

    @MainScope
    @Provides
    fun providesGetIsRemoteDataTurnedOnUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): IsRemoteDataTurnedOnUseCase = IsRemoteDataTurnedOnUseCase(sharedPreferencesRepository)

    @MainScope
    @Provides
    fun providesLocalSongsRepository(
        assetsStringReader: AssetsStringReader,
        gson: Gson
    ): LocalSongsRepository = LocalSongsRepository(assetsStringReader, gson)

    @MainScope
    @Provides
    fun providesAssetsStringReader() : AssetsStringReader = AssetsStringReader(activity)
}