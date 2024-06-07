package com.feroxdev.closetApp.utilities

import com.feroxdev.closetApp.R

class Helper {
    enum class ImageType (val int: Int){
        HEAD(10),
        UPPERBODY(20),
        LOWERBODY(30),
    }
    enum class HeadSubtype (val int: Int){
        BONNET(10),
        GLASSES(20),
        JEWELRY(30),
        OTHERS(40)
    }

    enum class UpperSubtype (val int: Int){
        JACKET(10),
        SHIRT(20),
        SWEATER(30),
        OTHERS(40)
    }

    enum class LowerSubtype (val int: Int){
        PANTS(10),
        SHORTS(20),
        SKIRT(30),
        BOTTOM(40)
    }

    enum class Subtype (val int: Int){
        ONE(10),
        TWO(20),
        THREE(30),
        FOUR(40)
    }

    companion object {
        fun categoryToString(category: Int): Int {
            return when (category) {
                ImageType.HEAD.int -> R.string.title_head
                ImageType.UPPERBODY.int -> R.string.title_upperbody
                ImageType.LOWERBODY.int -> R.string.title_lowerbody
                else -> -1
            }
        }
        fun categoryAndSubcategoryToString(category: Int, subcategory : Int): Int {
                if (category == ImageType.HEAD.int) {
                    return when (subcategory) {
                        HeadSubtype.BONNET.int -> R.string.hat
                        HeadSubtype.GLASSES.int -> R.string.glasses
                        HeadSubtype.JEWELRY.int -> R.string.jewelry
                        HeadSubtype.OTHERS.int -> R.string.others
                        else -> -1
                    }
                }
                if (category == ImageType.UPPERBODY.int) {
                    return when (subcategory) {
                        UpperSubtype.JACKET.int -> R.string.jacket
                        UpperSubtype.SHIRT.int -> R.string.tshirt
                        UpperSubtype.SWEATER.int -> R.string.sweater
                        UpperSubtype.OTHERS.int -> R.string.others
                        else -> -1
                    }
                }
                if (category == ImageType.LOWERBODY.int) {
                    return when (subcategory) {
                        LowerSubtype.PANTS.int -> R.string.pants
                        LowerSubtype.SHORTS.int -> R.string.shorts
                        LowerSubtype.SKIRT.int -> R.string.skirt
                        LowerSubtype.BOTTOM.int -> R.string.bottom
                        else -> -1
                    }
                }
            return -1
        }
    }
}