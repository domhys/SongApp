package com.songapp.application.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.songapp.BuildConfig
import com.songapp.repository.songs_remote.SongService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ServiceModule {

    @Provides
    fun providesSongService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): SongService {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SongService::class.java)
    }

    @Provides
    internal fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .readTimeout(HTTP_READ_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(HTTP_READ_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    companion object {
        private const val HTTP_READ_WRITE_TIMEOUT = 30
    }
}