package com.feroxdev.closetApp.ui.viewmodels.Collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.feroxdev.closetApp.data.repository.CollectionRepository
import com.feroxdev.closetApp.data.model.Collection
import kotlinx.coroutines.launch

class CollectionViewModel(private val repository: CollectionRepository) : ViewModel() {

    fun insert(collection: Collection) = viewModelScope.launch {
        repository.insert(collection)
    }

    fun getCollectionById(id: Int): LiveData<Collection?> = liveData {
        emit(repository.getCollectionById(id))
    }

    fun getAllCollections(): LiveData<List<Collection>> = liveData {
        emit(repository.getAllCollections())
    }
}