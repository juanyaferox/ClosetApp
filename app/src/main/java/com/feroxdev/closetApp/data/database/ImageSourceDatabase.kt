package com.feroxdev.closetApp.data.database;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.feroxdev.closetApp.data.dao.ImageSourceDAO

import com.feroxdev.closetApp.data.model.ImageSource;

@Database(entities = [ImageSource::class], version = 1, exportSchema = false)
abstract class ImageSourceDatabase : RoomDatabase() {
    abstract fun ImageSourceDAO(): ImageSourceDAO

    companion object {
        @Volatile
        private var INSTANCE: ImageSourceDatabase? = null

        fun getDatabase(context: Context): ImageSourceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageSourceDatabase::class.java,
                    "imagesource_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
