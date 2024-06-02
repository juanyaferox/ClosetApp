package com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.model.ImageSourceCollectionCrossRef
import com.feroxdev.closetApp.data.repository.ImageSourceCollectionRepository
import kotlinx.coroutines.launch

class ImageSourceCollectionViewModel(private val repository: ImageSourceCollectionRepository) : ViewModel() {

    fun insert(crossRef: ImageSourceCollectionCrossRef) = viewModelScope.launch {
        repository.insert(crossRef)
    }

    fun getImagesForCollection(collectionId: Int): LiveData<List<ImageSource>> = liveData {
        emit(repository.getImagesForCollection(collectionId))
    }
}