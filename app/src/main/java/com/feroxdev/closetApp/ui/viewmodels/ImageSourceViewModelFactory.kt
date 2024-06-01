package com.feroxdev.closetApp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.repository.ImageSourceRepository

class ImageSourceViewModelFactory (private val repository: ImageSourceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageSource::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageSourceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}