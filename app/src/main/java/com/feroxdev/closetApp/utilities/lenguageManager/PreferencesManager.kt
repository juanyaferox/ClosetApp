package com.feroxdev.closetApp.utilities.lenguageManager

import android.content.Context

class PreferencesManager {
    companion object {
        fun saveLanguagePreference(context: Context, language: String) {
            val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("My_Lang", language)
            editor.apply()
        }

        fun loadLanguagePreference(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return sharedPreferences.getString("My_Lang", "")
        }
    }
}

