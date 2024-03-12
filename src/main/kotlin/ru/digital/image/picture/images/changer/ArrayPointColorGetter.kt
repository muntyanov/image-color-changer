package ru.digital.image.picture.images.changer

interface ArrayPointColorGetter: PointColorGetter {
    operator fun get(colors: IntArray?): Int
}
