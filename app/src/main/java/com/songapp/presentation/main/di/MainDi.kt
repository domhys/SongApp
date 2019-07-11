package com.songapp.presentation.main.di

import com.songapp.domain.use_cases.GetLocalSongsUseCase
import com.songapp.presentation.main.MainActivity
import com.songapp.presentation.main.MainContract
import com.songapp.presentation.main.MainModel
import com.songapp.presentation.main.MainPresenter
import com.songapp.repository.songs_local.LocalSongsRepository
import com.songapp.utility.AssetsStringReader
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@MainScope
@Component(modules = [MainModule::class])
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
        getLocalSongsUseCase: GetLocalSongsUseCase
    ): MainModel = MainModel(getLocalSongsUseCase)


    @MainScope
    @Provides
    fun providesGetLocalSongsUseCase(
        localSongsRepository: LocalSongsRepository
    ): GetLocalSongsUseCase = GetLocalSongsUseCase(localSongsRepository)

    @MainScope
    @Provides
    fun providesLocalSongsRepository(
        assetsStringReader: AssetsStringReader
    ): LocalSongsRepository = LocalSongsRepository(assetsStringReader)

    @MainScope
    @Provides
    fun providesAssetsStringReader() : AssetsStringReader = AssetsStringReader(activity)
}