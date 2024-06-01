package com.feroxdev.closetApp.data.repository

import com.feroxdev.closetApp.data.dao.ImageSourceDAO
import com.feroxdev.closetApp.data.model.ImageSource

class ImageSourceRepository(private val imageSourceDAO: ImageSourceDAO) {
    suspend fun insertImage (imageSource: ImageSource) {
        imageSourceDAO.insert(imageSource)
    }

    suspend fun deleteImage (imageSource: ImageSource) {
        imageSourceDAO.delete(imageSource)
    }

    suspend fun getImages (): List<ImageSource> {
        return imageSourceDAO.getAllImages()
    }

    suspend fun updateImage (imageSource: ImageSource) {
        imageSourceDAO.update(imageSource)
    }
}