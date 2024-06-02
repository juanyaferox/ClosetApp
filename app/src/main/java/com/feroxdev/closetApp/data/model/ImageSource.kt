package com.feroxdev.closetApp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageSource (
    @PrimaryKey(autoGenerate = true) val idImage: Int = 0,
    val generatedName: String,
    val path: String,
    val category: Int,
    val subcategory: Int
)