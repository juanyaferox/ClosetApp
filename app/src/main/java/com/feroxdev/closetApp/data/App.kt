package com.feroxdev.closetApp.data

import android.app.Application
import com.feroxdev.closetApp.data.database.AppDatabase
import com.feroxdev.closetApp.data.repository.CollectionRepository
import com.feroxdev.closetApp.data.repository.ImageSourceCollectionRepository
import com.feroxdev.closetApp.data.repository.ImageSourceRepository

class App : Application() {

    // Singleton para la base de datos y repositorios
    val database by lazy { AppDatabase.getDatabase(this) }
    val imageSourceRepository by lazy { ImageSourceRepository(database.imageSourceDAO()) }
    val collectionRepository by lazy { CollectionRepository(database.collectionDAO()) }
    val imageSourceCollectionRepository by lazy { ImageSourceCollectionRepository(database.imageSourceCollectionDAO()) }
}