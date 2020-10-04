package com.example.haikupuzzle.util

import android.content.Context
import android.content.SharedPreferences
import com.example.haikupuzzle.data.HaikuModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPreferences(context: Context) {
    companion object {
        private const val HAIKU = "myHaiku"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(HAIKU, 0)

    fun applySharedPreference(haikuModels: HaikuModels) {
        val getHaikuModels = getSharedPreference()
        val setGson = if (getHaikuModels.isNullOrEmpty()) {
            Gson().toJson(mutableListOf(haikuModels))
        } else {
            getHaikuModels.add(haikuModels)
            Gson().toJson(getHaikuModels)
        }
        preferences.edit()
            .putString(HAIKU, setGson)
            .apply()
    }

    fun getSharedPreference(): MutableList<HaikuModels> {
        val getGson = Gson()
        val json = preferences.getString(HAIKU, "")
        println("song---get sharepre $json")
        return if (json.isNullOrEmpty()) {
            println("song---get sharepre is null or empty")
            mutableListOf()
        } else {
            println("song---get sharepre is not null or empty")
            val type = object : TypeToken<MutableList<HaikuModels>?>() {}.type
            getGson.fromJson(json, type)
        }
    }
}