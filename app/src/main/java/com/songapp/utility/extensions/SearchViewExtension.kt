package com.songapp.utility.extensions

import androidx.appcompat.widget.SearchView
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun SearchView.setOnQueryChange(
    delay: Long,
    onQueryTextSubmit: (String) -> Boolean,
    onQueryTextChange: (String) -> Boolean,
    subscribeScheduler: Scheduler = Schedulers.computation()
): SearchView.OnQueryTextListener {
    val listener = DelayedListener(delay, onQueryTextSubmit, onQueryTextChange, subscribeScheduler)
    setOnQueryTextListener(listener)
    return listener
}

private class DelayedListener(
    private val delay: Long,
    private val onQueryTextSubmitCallback: (String) -> Boolean,
    private val onQueryTextChangeCallback: (String) -> Boolean,
    private val scheduler: Scheduler = Schedulers.computation()
) : SearchView.OnQueryTextListener {

    private var queryChangeDisposable: Disposable? = null

    override fun onQueryTextSubmit(query: String): Boolean = this.onQueryTextSubmitCallback(query)

    override fun onQueryTextChange(newText: String): Boolean {
        if (delay == 0L) {
            onQueryTextChangeCallback(newText)
            return true
        }

        queryChangeDisposable?.dispose()
        queryChangeDisposable = Single.just(onQueryTextChangeCallback)
            .delaySubscription(delay, TimeUnit.MILLISECONDS)
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { queryChange -> queryChange(newText) }
        return true
    }
}