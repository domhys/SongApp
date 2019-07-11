package com.songapp.presentation.main.di

import com.songapp.presentation.main.MainActivity
import com.songapp.presentation.main.MainContract
import com.songapp.presentation.main.MainPresenter
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
    fun providesPresenter(): MainContract.Presenter = MainPresenter()
}