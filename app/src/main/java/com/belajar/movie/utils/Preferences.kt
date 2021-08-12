package com.belajar.movie.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context) {
    companion object {
        const val USER_PREF = "USER_PREF"
    }

    private val sharedPreferences = context.getSharedPreferences(USER_PREF, 0)

    fun setValue(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value).apply()
    }

    fun setValue(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    fun getValueString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getValueBool(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}