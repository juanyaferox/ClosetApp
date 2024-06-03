package com.feroxdev.closetApp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.time.LocalDateTime
import java.util.Date

@Entity
data class ImageSource (
    @PrimaryKey(autoGenerate = true) val idImage: Int = 0,
    val path: String,
    val customName : String,
    val category: Int,
    val subcategory: Int,
    val dischargeDate: String
)