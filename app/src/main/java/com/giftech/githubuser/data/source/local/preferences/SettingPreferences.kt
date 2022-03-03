package com.giftech.githubuser.data.source.local.preferences

import android.annotation.SuppressLint
import android.content.Context

class SettingPreferences private constructor(val context:Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setDarkTheme(isDark:Boolean){
        val editor = preferences.edit()
        editor.putBoolean(DARK_THEME, isDark)
        editor.apply()
    }

    fun getIsDarkTheme():Boolean = preferences.getBoolean(DARK_THEME, false)

    companion object {

        private const val PREFS_NAME = "settings_pref"
        private const val DARK_THEME = "DARK_THEME"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(context: Context): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(context)
                INSTANCE = instance
                instance
            }
        }
    }
}