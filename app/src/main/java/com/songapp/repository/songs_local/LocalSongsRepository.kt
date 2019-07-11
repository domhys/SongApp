package com.songapp.repository.songs_local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.songapp.domain.model.Song
import com.songapp.utility.AssetsStringReader
import java.lang.reflect.Type

class LocalSongsRepository(
    private val assetsStringReader: AssetsStringReader
) {

    fun getSongs(): List<Song> {
        return readJson(SONGS_PATH, object : TypeToken<List<Song>>() {}.type)
    }

    private fun <DataType> readJson(jsonPath: String, type: Type): DataType {
        return Gson().fromJson(assetsStringReader.readFromAssets(jsonPath), type)
    }

    companion object {
        private const val SONGS_PATH = "songs-list.json"
    }
}