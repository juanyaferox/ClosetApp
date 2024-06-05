package com.feroxdev.closetApp.utilities

import android.content.Context
import java.util.Locale

object PreferenceUtils {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_LANGUAGE = "key_language"

    fun setLanguage(context: Context, language: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, language).apply()
    }

    fun getLanguage(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, Locale.getDefault().language)
    }
}