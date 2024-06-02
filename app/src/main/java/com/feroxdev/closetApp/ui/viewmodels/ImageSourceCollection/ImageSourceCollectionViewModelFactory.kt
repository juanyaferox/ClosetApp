package com.feroxdev.closetApp.ui.viewmodels.ImageSourceCollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feroxdev.closetApp.data.repository.ImageSourceCollectionRepository

class ImageSourceCollectionViewModelFactory(private val repository: ImageSourceCollectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageSourceCollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageSourceCollectionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}