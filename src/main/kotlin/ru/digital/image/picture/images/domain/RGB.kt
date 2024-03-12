package ru.digital.image.picture.images.domain

import java.util.*

class RGB {

    constructor(color: Int) {
        red = color shr 16 and 0xFF
        green = color shr 8 and 0xFF
        blue = color and 0xFF
    }


    var red: Int = 0
    var green: Int = 0
    var blue: Int = 0
    fun RGB(rgb: Int) {
        red = rgb shr 16 and 0xFF
        green = rgb shr 8 and 0xFF
        blue = rgb and 0xFF
    }

    fun RGB(r: Int, g: Int, b: Int) {
        red = r
        green = g
        blue = b
    }

    fun toInt(): Int {
        var rgb = red
        rgb = (rgb shl 8) + green
        rgb = (rgb shl 8) + blue
        return rgb
    }

    fun toInverseInt(): Int {
        var rgb = 255 - red
        rgb = (rgb shl 8) + 255 - green
        rgb = (rgb shl 8) + 255 - blue
        return rgb
    }

    fun toGrey(): Int {
        return (red + green + blue) / 3
    }

    fun mse(rbg: RGB): Double {
        return MSE(
            this.red, rbg.red,
            this.green, rbg.green,
            this.blue, rbg.blue
        )
    }

    fun max(rbg: RGB): Double {
        return MAX(
            red, rbg.red,
            green, rbg.green,
            blue, rbg.blue
        )
    }
}

fun MSE(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int): Double {
    return Math.sqrt(
        (
            (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2)
            ).toDouble()
    )
}

fun MAX(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int): Double {
    return Arrays.stream(
        doubleArrayOf(
            Math.abs(r1 - r2) * 1.0,
            Math.abs(g1 - g2) * 1.0,
            Math.abs(b1 - b2) * 1.0
        )
    ).max().asDouble
}
