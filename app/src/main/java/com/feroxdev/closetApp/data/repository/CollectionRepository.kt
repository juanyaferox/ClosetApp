package com.feroxdev.closetApp.data.repository

import com.feroxdev.closetApp.data.dao.CollectionDAO
import com.feroxdev.closetApp.data.model.Collection

class CollectionRepository(private val collectionDAO: CollectionDAO) {
    suspend fun insert(collection: Collection) = collectionDAO.insert(collection)
    suspend fun getCollectionById(id: Int) = collectionDAO.getCollectionById(id)
    suspend fun getAllCollections() = collectionDAO.getAllCollections()
}