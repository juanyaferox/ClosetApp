package com.feroxdev.closetApp.ui.viewmodels.ImageSource

import androidx.lifecycle.*
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.repository.ImageSourceRepository
import kotlinx.coroutines.launch

class ImageSourceViewModel(private val repository: ImageSourceRepository) : ViewModel() {
    fun insert(imageSource: ImageSource) = viewModelScope.launch {
        repository.insert(imageSource)
    }

    fun getImageById(id: Int): LiveData<ImageSource?> = liveData {
        emit(repository.getImageById(id))
    }

    fun getAllImages(): LiveData<List<ImageSource>> = liveData {
        emit(repository.getAllImages())

    }
}