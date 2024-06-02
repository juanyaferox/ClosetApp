package com.feroxdev.closetApp.data.repository

import com.feroxdev.closetApp.data.dao.ImagesourceCollectionDAO
import com.feroxdev.closetApp.data.model.ImageSourceCollectionCrossRef

class ImageSourceCollectionRepository(private val crossRefDAO: ImagesourceCollectionDAO)  {
    suspend fun insert(crossRef: ImageSourceCollectionCrossRef) = crossRefDAO.insert(crossRef)
    suspend fun getImagesForCollection(collectionId: Int) = crossRefDAO.getImagesForCollection(collectionId)
}