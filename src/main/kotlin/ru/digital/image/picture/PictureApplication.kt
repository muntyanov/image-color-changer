package ru.digital.image.picture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PictureApplication

/*
        ImageColorChange icm = new ImageColorChange(
                Arrays.asList(
                        new DiffPointColorGetter(true, 1),
                    new MinMaxColorOfPointGetter(new int[] {200,400,600,800, 1000})

                ));

        try {
            File url = new File( "/Users/s.muntyanov/Downloads/U2k0MWxVDN4.jpeg");
            var img = ImageIO.read(url);
            icm.run(img);
            ImageIO.write(img,  "png", new File("/Users/s.muntyanov/Downloads/1.png"));

        } catch (IOException e) {
            System.out.println(e);
        }

 */
fun main(args: Array<String>) {
	runApplication<PictureApplication>(*args)
}
