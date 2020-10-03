package com.example.haikupuzzle.util

import android.content.Context
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class JsonResourcesUtils {
    fun getJsonWordList(context: Context, jsonFileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(jsonFileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Timber.e("json error")
            return null
        }
    }
}