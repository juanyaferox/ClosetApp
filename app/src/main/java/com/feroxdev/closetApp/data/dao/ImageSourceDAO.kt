package com.feroxdev.closetApp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feroxdev.closetApp.data.model.ImageSource

@Dao
interface ImageSourceDAO {
    @Insert
    suspend fun insert(imageSource: ImageSource)

    @Update
    suspend fun update(imageSource: ImageSource)

    @Delete
    suspend fun delete(imageSource: ImageSource)

    @Query("SELECT * FROM image_source")
    suspend fun getAllImages(): List<ImageSource>

    ///ETC
}