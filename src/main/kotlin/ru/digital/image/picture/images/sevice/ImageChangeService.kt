package ru.digital.image.picture.images.sevice

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import ru.digital.image.picture.images.changer.ImageColorChange
import ru.digital.image.picture.images.changer.MinMaxColorOfPointGetter
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

@Service
class ImageChangeService {

    @PostConstruct
    fun init() {
        change("/Users/s.muntyanov/Downloads/1.jpg",
            "/Users/s.muntyanov/Downloads/031.jpg")
    }

    fun change(imgPath: String, imgDestPath: String) {
        val icm = ImageColorChange(
            listOf(
                MinMaxColorOfPointGetter(intArrayOf(200, 400, 600, 800, 1000, 6000, 10000))
            )
        )

        try {
            val url = File(imgPath)
            val img: BufferedImage = ImageIO.read(url)
            icm.run(img)
            ImageIO.write(img, "png", File(imgDestPath))
        } catch (e: IOException) {
            println(e)
        }
    }
}
