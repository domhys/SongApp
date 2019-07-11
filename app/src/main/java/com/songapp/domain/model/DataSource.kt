package com.songapp.domain.model

enum class DataSource(val index: Int) {
    LOCAL(0), REMOTE(1), INVALID(-1);

    companion object {
        fun createFromIndex(index: Int) =
            when (index) {
                0 -> LOCAL
                1 -> REMOTE
                else -> INVALID
            }
    }
}