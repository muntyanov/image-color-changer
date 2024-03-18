package ru.digital.image.picture.images.sevice

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import ru.digital.image.picture.images.changer.ImageColorChange
import ru.digital.image.picture.images.changer.MinMaxColorOfPointGetter
import ru.digital.image.picture.images.domain.RGB
import java.awt.AlphaComposite
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.HexFormat
import javax.imageio.ImageIO
import kotlin.math.max


@Service
class ImageChangeService {

    @PostConstruct
    fun init() {
        change("/Users/s.muntyanov/Downloads/1.jpg",
            "/Users/s.muntyanov/Downloads/031.jpg")
    }

    fun createResizedCopy(
        originalImage: Image?,
        scaledWidth: Int, scaledHeight: Int,
        preserveAlpha: Boolean
    ): BufferedImage {
        println("resizing...")
        val imageType = if (preserveAlpha) BufferedImage.TYPE_INT_RGB else BufferedImage.TYPE_INT_ARGB
        val scaledBI = BufferedImage(scaledWidth, scaledHeight, imageType)
        val g = scaledBI.createGraphics()
        if (preserveAlpha) {
            g.composite = AlphaComposite.Src
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null)
        g.dispose()
        return scaledBI
    }


    fun findScale(
        height: Int, width: Int,
    ): Pair<Int, Int> {
        val max = if(max(height, width) > 1000) {
            max(height, width) / 1000
        } else {
            1
        }
        return height/max to width/max
    }

    fun change(imgPath: String, imgDestPath: String) {
        val icm = ImageColorChange(
            listOf(
                MinMaxColorOfPointGetter(
                    intArrayOf(
                        RGB(0, 0, 0).toInt(),
                        RGB(255, 255, 255).toInt(),
                        RGB(255, 0, 0).toInt(),
                        RGB(0, 255, 0).toInt(),
                        RGB(0, 0, 255).toInt(),
                        RGB(122, 122, 0).toInt(),
                        RGB(0, 122, 122).toInt(),
                        RGB(122, 0, 122).toInt(),
                        RGB(122, 0, 0).toInt(),
                        RGB(0, 122, 0).toInt(),
                        RGB(0, 0, 122).toInt(),
                    )
                )
            )
        )

        try {
            val url = File(imgPath)
            val img: BufferedImage = ImageIO.read(url)
            val scale = findScale(img.height, img.width)
            val scaledImage = createResizedCopy(img, scale.second, scale.first, true )
            icm.run(scaledImage)
            ImageIO.write(scaledImage, "png", File(imgDestPath))
        } catch (e: IOException) {
            println(e)
        }
    }
}
