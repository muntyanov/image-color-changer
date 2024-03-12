package ru.digital.image.picture.images.changer

import ru.digital.image.picture.images.domain.RGB
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

class MinMaxColorOfPointGetter(defaultColors: IntArray?) : PointColorGetter {
    operator fun get(color: Int): Int {
        val current = RGB(color)
        val obj = defaultColors.stream().map { it: RGB ->
            arrayOf(
                it,
                it.max(current)
            )
        }.min { it1: Array<Any>, it2: Array<Any> ->
            if (it1[1] as Double > it2[1] as Double
            ) return@min 1 else if (it1[1] as Double == it2[1] as Double) return@min 0 else return@min -1
        }.get()
        return (obj[0] as RGB).toInt()
    }

    private val defaultColors: List<RGB>

    init {
        this.defaultColors =
            Arrays.stream(defaultColors).mapToObj<Any> { it: Int -> RGB(it) }.collect(Collectors.toList()) as List<RGB>
    }
}
