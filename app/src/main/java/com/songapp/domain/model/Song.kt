package com.songapp.domain.model

import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("Song Clean") val title: String = NO_DATA,
    @SerializedName("ARTIST CLEAN") val artist: String = NO_DATA,
    @SerializedName("Release Year") val releaseYear: String = NO_DATA
) {
    companion object {
        private const val NO_DATA = "No Data"
    }
}