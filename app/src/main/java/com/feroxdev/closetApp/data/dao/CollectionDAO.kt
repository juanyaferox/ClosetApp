package com.feroxdev.closetApp.data.dao

import androidx.room.*
import com.feroxdev.closetApp.data.model.Collection

@Dao
interface CollectionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collection: Collection): Long

    @Query("SELECT * FROM Collection WHERE idCollection = :id")
    suspend fun getCollectionById(id: Int): Collection?

    @Query("SELECT * FROM Collection")
    suspend fun getAllCollections(): List<Collection>
}