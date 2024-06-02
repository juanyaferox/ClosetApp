package com.feroxdev.closetApp.data.model

import androidx.room.Entity

@Entity (primaryKeys = ["idImageSource", "idCollection"])
data class ImageSourceCollectionCrossRef(
    val idImageSource: Int,
    val idCollection: Int
)