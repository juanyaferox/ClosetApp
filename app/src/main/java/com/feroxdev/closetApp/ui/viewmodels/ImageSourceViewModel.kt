package com.feroxdev.closetApp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.repository.ImageSourceRepository
import kotlinx.coroutines.launch

class ImageSourceViewModel(private val repository: ImageSourceRepository) : ViewModel() {
    private val _images = MutableLiveData<List<ImageSource>>()
    val images: LiveData<List<ImageSource>> get() = _images

    // LiveData para manejar eventos de inserción
    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> get() = _inserted

    // LiveData para manejar eventos de eliminación
    private val _deleted = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> get() = _deleted

    // LiveData para manejar eventos de actualización
    private val _updated = MutableLiveData<Boolean>()
    val updated: LiveData<Boolean> get() = _updated

    // Función para insertar una imagen
    fun insertImage(imageSource: ImageSource) = viewModelScope.launch {
        try {
            repository.insertImage(imageSource)
            _inserted.value = true
        } catch (e: Exception) {
            _inserted.value = false
        }
    }

    // Función para eliminar una imagen
    fun deleteImage(imageSource: ImageSource) = viewModelScope.launch {
        try {
            repository.deleteImage(imageSource)
            _deleted.value = true
        } catch (e: Exception) {
            _deleted.value = false
        }
    }

    // Función para obtener todas las imágenes
    fun getImages() = viewModelScope.launch {
        try {
            val imagesList = repository.getImages()
            _images.value = imagesList
        } catch (e: Exception) {
            _images.value = emptyList()
        }
    }

    // Función para actualizar una imagen
    fun updateImage(imageSource: ImageSource) = viewModelScope.launch {
        try {
            repository.updateImage(imageSource)
            _updated.value = true
        } catch (e: Exception) {
            _updated.value = false
        }
    }
}