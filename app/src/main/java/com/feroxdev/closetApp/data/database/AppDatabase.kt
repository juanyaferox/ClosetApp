package com.feroxdev.closetApp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.feroxdev.closetApp.data.dao.CollectionDAO
import com.feroxdev.closetApp.data.dao.ImageSourceDAO
import com.feroxdev.closetApp.data.dao.ImagesourceCollectionDAO
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.model.ImageSourceCollectionCrossRef
import com.feroxdev.closetApp.data.model.Collection

@Database(entities = [ImageSource::class, Collection::class, ImageSourceCollectionCrossRef::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun imageSourceDAO(): ImageSourceDAO
    abstract fun collectionDAO(): CollectionDAO
    abstract fun imageSourceCollectionDAO(): ImagesourceCollectionDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}