package com.feroxdev.closetApp.utilities.lenguageManager

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class LocaleManager {
    companion object {
        fun setLocale(context: Context, language: String) {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }
}