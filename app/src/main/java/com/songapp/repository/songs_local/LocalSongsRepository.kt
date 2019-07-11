package com.songapp.repository.songs_local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.songapp.domain.model.Song
import com.songapp.domain.model.SongLocalEntity
import com.songapp.utility.AssetsStringReader
import java.lang.reflect.Type

class LocalSongsRepository(
    private val assetsStringReader: AssetsStringReader,
    private val gson: Gson
) {

    fun getSongs(): List<Song> {
        return readJson<List<SongLocalEntity>>(SONGS_PATH, object : TypeToken<List<SongLocalEntity>>() {}.type)
            .map(::Song)
    }

    private fun <DataType> readJson(jsonPath: String, type: Type): DataType {
        return gson.fromJson(assetsStringReader.readFromAssets(jsonPath), type)
    }

    companion object {
        private const val SONGS_PATH = "songs-list.json"
    }
}