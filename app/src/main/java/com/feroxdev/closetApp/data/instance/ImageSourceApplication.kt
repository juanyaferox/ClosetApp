package com.feroxdev.closetApp.data.instance

import android.app.Application
import com.feroxdev.closetApp.data.database.ImageSourceDatabase
import com.feroxdev.closetApp.data.repository.ImageSourceRepository

class ImageSourceApplication : Application() {
    val database by lazy { ImageSourceDatabase.getDatabase(this) }
    val repository by lazy { ImageSourceRepository(database.ImageSourceDAO()) }
}