package com.feroxdev.closetApp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ImageSource (
    @PrimaryKey(autoGenerate = true) val idImage: Int = 0,
    val path: String,
    val customName : String,
    val category: Int,
    val subcategory: Int,
    val dischargeDate: String
)