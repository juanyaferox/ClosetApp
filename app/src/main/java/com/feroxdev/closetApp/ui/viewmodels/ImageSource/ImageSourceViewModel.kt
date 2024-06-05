package com.feroxdev.closetApp.ui.viewmodels.ImageSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.feroxdev.closetApp.data.model.ImageSource
import com.feroxdev.closetApp.data.repository.ImageSourceRepository
import kotlinx.coroutines.launch

class ImageSourceViewModel(private val repository: ImageSourceRepository) : ViewModel() {
    private val _imageSourceList = MutableLiveData<List<ImageSource>>()
    val imageSourceList: LiveData<List<ImageSource>> get() = _imageSourceList
    fun insert(imageSource: ImageSource) = viewModelScope.launch {
        repository.insert(imageSource)
    }

    fun getImageById(id: Int): LiveData<ImageSource?> = liveData {
        emit(repository.getImageById(id))
    }

    fun getAllImages(): LiveData<List<ImageSource>> = liveData {
        emit(repository.getAllImages())

    }
    fun getImagesByCategory(category: Int): LiveData<List<ImageSource>> = liveData {
        emit(repository.getImagesByCategory(category))

    }
    fun getImagesByCategoryAndSubcategory(category: Int, subcategory: Int): LiveData<List<ImageSource>> = liveData {
        emit(repository.getImagesByCategoryAndSubcategory(category, subcategory))

    }

    fun setImageSourceList(list: List<ImageSource>) {
        _imageSourceList.value = list
    }

}