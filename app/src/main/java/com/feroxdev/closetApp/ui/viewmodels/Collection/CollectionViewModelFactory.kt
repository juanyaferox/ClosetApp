package com.feroxdev.closetApp.ui.viewmodels.Collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feroxdev.closetApp.data.repository.CollectionRepository

class CollectionViewModelFactory(private val repository: CollectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CollectionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
