package ru.digital.image.picture.images.changer

interface OnePointColorGetter: PointColorGetter {
    operator fun get(color: Int): Int
}
