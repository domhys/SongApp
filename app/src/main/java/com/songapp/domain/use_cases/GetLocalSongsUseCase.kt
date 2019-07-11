package com.songapp.domain.use_cases

import com.songapp.domain.model.Song
import com.songapp.repository.songs_local.LocalSongsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetLocalSongsUseCase(
    private val localSongsRepository: LocalSongsRepository
) {

    fun getSongs(): Single<List<Song>> =
        Single.fromCallable { localSongsRepository.getSongs() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}