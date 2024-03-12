package ru.digital.image.picture.images;

import com.example.demo.images.ArrayPointColorGetter;
import com.example.demo.images.OnePointColorGetter;
import com.example.demo.images.PointColorGetter;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageColorChange {

    private boolean hasTwoPointsColor;
    List<PointColorGetter> pointColorGetters;
    public ImageColorChange(List<PointColorGetter> pointColorGetters){
        this.pointColorGetters = pointColorGetters;
    }
    public void run(BufferedImage image){
        for(int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x,y);
                for(PointColorGetter getter : pointColorGetters){
                    if(getter instanceof OnePointColorGetter){
                        rgb = ((OnePointColorGetter)getter).get(image.getRGB(x,y));
                    }
                    if(getter instanceof ArrayPointColorGetter){
                        if(x < image.getWidth() -1 && y < image.getHeight() -1)
                            rgb = ((ArrayPointColorGetter)getter).get(
                                    new int[]{
                                    image.getRGB(x,y),
                                            applyBefore(image, x,y+1, getter),
                                            applyBefore(image, x+1,y, getter)});
                    }
                }
                image.setRGB(x, y, rgb);
            }
        }
    }

    private int applyBefore(BufferedImage image, int x,int y, PointColorGetter pointColorGetter){
        int rgb  = image.getRGB(x,y);
        for(PointColorGetter getter : pointColorGetters){
            if(getter == pointColorGetter){
                break;
            }

            if(getter instanceof OnePointColorGetter){
                rgb = ((OnePointColorGetter)getter).get(rgb);
            }
            if(getter instanceof ArrayPointColorGetter){
                if(x < image.getWidth() -1 && y > image.getHeight() -1)
                    rgb = ((ArrayPointColorGetter)getter).get(
                            new int[]{
                                    rgb,
                                    applyBefore(image, x,y+1, getter),
                                    applyBefore(image, x+1,y, getter)});
            }
        }
        return rgb;
    }
}
