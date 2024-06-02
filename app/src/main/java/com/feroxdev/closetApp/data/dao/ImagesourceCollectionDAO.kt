package com.feroxdev.closetApp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.feroxdev.closetApp.data.model.ImageSourceCollectionCrossRef
import com.feroxdev.closetApp.data.model.ImageSource

@Dao
interface ImagesourceCollectionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crossRef: ImageSourceCollectionCrossRef): Long

    @Query(
        """
        SELECT * FROM ImageSource
        INNER JOIN ImageSourceCollectionCrossRef ON ImageSource.idImage = ImageSourceCollectionCrossRef.idImageSource
        WHERE ImageSourceCollectionCrossRef.idCollection = :collectionId
    """
    )
    suspend fun getImagesForCollection(collectionId: Int): List<ImageSource>
}