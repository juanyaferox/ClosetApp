package com.feroxdev.closetApp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    @PrimaryKey(autoGenerate = true) val idCollection: Int,
    val collectionName : String
)