package com.feroxdev.closetApp.data.repository

import com.feroxdev.closetApp.data.dao.ImageSourceDAO
import com.feroxdev.closetApp.data.model.ImageSource

class ImageSourceRepository(private val imageSourceDAO: ImageSourceDAO) {

    suspend fun insert(imageSource: ImageSource) = imageSourceDAO.insert(imageSource)
    suspend fun getImageById(id: Int) = imageSourceDAO.getImageById(id)
    suspend fun getAllImages() = imageSourceDAO.getAllImages()
    suspend fun deleteImage (imageSource: ImageSource) = imageSourceDAO.delete(imageSource)

}