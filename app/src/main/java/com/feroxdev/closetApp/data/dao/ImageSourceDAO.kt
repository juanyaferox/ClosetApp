package com.feroxdev.closetApp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.feroxdev.closetApp.data.model.ImageSource

@Dao
interface ImageSourceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageSource: ImageSource): Long

    @Update
    suspend fun update(imageSource: ImageSource)

    @Delete
    suspend fun delete(imageSource: ImageSource)

    @Query("SELECT * FROM ImageSource")
    suspend fun getAllImages(): List<ImageSource>

    @Query("SELECT * FROM ImageSource WHERE idImage = :id")
    suspend fun getImageById(id: Int): ImageSource?

    @Query("SELECT * FROM ImageSource WHERE category = :category")
    suspend fun getImagesByCategory(category: Int): List<ImageSource>
    @Query("SELECT * FROM ImageSource WHERE category = :category AND subcategory = :subcategory")
    suspend fun getImageByCategoryAndSubcategory(category: Int, subcategory: Int): List<ImageSource>



    ///ETC
}