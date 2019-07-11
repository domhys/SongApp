package com.songapp.utility

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

open class AssetsStringReader(private val context: Context) {

    private val charSet = "UTF-8"

    open fun readFromAssets(fileName: String): String? {
        val fileContentStringBuilder = StringBuilder()
        val fileInputStream = context.assets.open(fileName)
        val fileStreamReader = BufferedReader(InputStreamReader(fileInputStream, charSet))

        fileStreamReader.forEachLine {
                line ->
            fileContentStringBuilder.append(line)
        }
        fileStreamReader.close()
        return fileContentStringBuilder.toString()
    }
}
