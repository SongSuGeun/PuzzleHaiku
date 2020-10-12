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
        getHaikuModels.add(haikuModels)
        val setGson = Gson().toJson(getHaikuModels)
        preferences.edit()
            .putString(HAIKU, setGson)
            .apply()
    }

    fun getSharedPreference(): MutableList<HaikuModels> {
        val getGson = Gson()
        val json = preferences.getString(HAIKU, "")
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            val type = object : TypeToken<List<HaikuModels>?>() {}.type
            getGson.fromJson(json, type)
        }
    }

    fun removeSharedPreference(position: Int): MutableList<HaikuModels> {
        val currentCalendarModel = getSharedPreference()
        clearSharedPreference()
        if (currentCalendarModel.isNotEmpty()) {
            currentCalendarModel.removeAt(position)
            val setGson = Gson().toJson(currentCalendarModel)
            preferences.edit()
                .putString(HAIKU, setGson)
                .apply()
        }
        return currentCalendarModel
    }

    private fun clearSharedPreference() {
        preferences.edit().remove(HAIKU).apply()
    }
}