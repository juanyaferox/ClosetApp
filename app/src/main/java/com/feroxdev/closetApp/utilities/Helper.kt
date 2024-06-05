package com.feroxdev.closetApp.utilities

public class Helper {
    public enum class ImageType (val int: Int){
        HEAD(10),
        UPPERBODY(20),
        LOWERBODY(30),
    }
    public enum class HeadSubtype (val int: Int){
        BONNET(10),
        GLASSES(20),
        JEWELRY(30),
        OTHERS(40)
    }

    public enum class UpperSubtype (val int: Int){
        JACKET(10),
        SHIRT(20),
        SWEATER(30),
        OTHERS(40)
    }

    public enum class LowerSubtype (val int: Int){
        PANTS(10),
        SHORTS(20),
        SKIRT(30),
        BOTTOM(40)
    }

    public enum class Subtype (val int: Int){
        ONE(10),
        TWO(20),
        THREE(30),
        FOUR(40)
    }
}