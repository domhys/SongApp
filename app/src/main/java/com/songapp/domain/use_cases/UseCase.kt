package com.songapp.domain.use_cases

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class UseCase<T>(protected val scheduler: Scheduler) {

    open fun perform(): Single<T> {
        return doWork()
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
    }

    open fun performOnTheSameThread(): Single<T> {
        return doWork()
    }

    protected abstract fun doWork(): Single<T>
}