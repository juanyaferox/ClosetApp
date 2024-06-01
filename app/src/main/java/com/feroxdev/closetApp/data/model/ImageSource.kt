package com.feroxdev.closetApp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_source")
data class ImageSource (
    @PrimaryKey(autoGenerate = true) val idImageSource: Int = 0,
    val generatedName: String,
    val sourceName: String,
    val path: String,
    val category: Int
)