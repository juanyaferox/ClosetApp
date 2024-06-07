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
        fun categoryToString(category: Int): String {
            return when (category) {
                ImageType.HEAD.int -> R.string.title_head.toString()
                ImageType.UPPERBODY.int -> R.string.title_upperbody.toString()
                ImageType.LOWERBODY.int -> R.string.title_lowerbody.toString()
                else -> ""
            }
        }
        fun categoryAndSucategoryToString(category: Int, subcategory : Int): String {
                if (category == ImageType.HEAD.int) {
                    return when (subcategory) {
                        HeadSubtype.BONNET.int -> R.string.hat.toString()
                        HeadSubtype.GLASSES.int -> R.string.glasses.toString()
                        HeadSubtype.JEWELRY.int -> R.string.jewelry.toString()
                        HeadSubtype.OTHERS.int -> R.string.others.toString()
                        else -> ""
                    }
                }
                if (category == ImageType.UPPERBODY.int) {
                    return when (subcategory) {
                        UpperSubtype.JACKET.int -> R.string.jacket.toString()
                        UpperSubtype.SHIRT.int -> R.string.tshirt.toString()
                        UpperSubtype.SWEATER.int -> R.string.sweater.toString()
                        UpperSubtype.OTHERS.int -> R.string.others.toString()
                        else -> ""
                    }
                }
                if (category == ImageType.LOWERBODY.int) {
                    return when (subcategory) {
                        LowerSubtype.PANTS.int -> R.string.pants.toString()
                        LowerSubtype.SHORTS.int -> R.string.shorts.toString()
                        LowerSubtype.SKIRT.int -> R.string.skirt.toString()
                        LowerSubtype.BOTTOM.int -> R.string.bottom.toString()
                        else -> ""
                    }
                }
            return ""
        }
    }
}