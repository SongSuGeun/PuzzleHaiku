package com.example.haikupuzzle.util

import com.squareup.moshi.Moshi
import java.lang.RuntimeException
import java.nio.Buffer

class JsonResourcesUtils {
    // TODO json파일 불러오기
    fun readJsonFile(target: String) : String{
        javaClass.getResourceAsStream("/$target").use {
            if (it == null) return "{}"

            val buffer = ByteArray(it.available())
            it.read(buffer)

            return String(buffer, Charsets.UTF_8)
        }
    }

    inline fun <reified T> toEntity(target: String): T {
        val body = readJsonFile(target)

        return Moshi.Builder().build().adapter(T::class.java).fromJson(body)
            ?: throw RuntimeException("failed load data")
    }
}