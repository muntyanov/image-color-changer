package ru.digital.image.picture.images.changer

import java.awt.image.BufferedImage

class ImageColorChange(
    val pointColorGetters: List<PointColorGetter>?
) {

    fun run(image: BufferedImage) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                var rgb = image.getRGB(x, y)
                for (getter in pointColorGetters!!) {
                    if (getter is OnePointColorGetter) {
                        rgb = getter[image.getRGB(x, y)]
                    }
                    if (getter is ArrayPointColorGetter) {
                        if (x < image.width - 1 && y < image.height - 1) rgb = getter[intArrayOf(
                            image.getRGB(x, y),
                            applyBefore(image, x, y + 1, getter),
                            applyBefore(image, x + 1, y, getter)
                        )]
                    }
                }
                image.setRGB(x, y, rgb)
            }
        }
    }

    private fun applyBefore(image: BufferedImage, x: Int, y: Int, pointColorGetter: PointColorGetter): Int {
        var rgb = image.getRGB(x, y)
        for (getter in pointColorGetters!!) {
            if (getter === pointColorGetter) {
                break
            }
            if (getter is OnePointColorGetter) {
                rgb = getter[rgb]
            }
            if (getter is ArrayPointColorGetter) {
                if (x < image.width - 1 && y > image.height - 1) rgb = getter[intArrayOf(
                    rgb,
                    applyBefore(image, x, y + 1, getter),
                    applyBefore(image, x + 1, y, getter)
                )]
            }
        }
        return rgb
    }
}
