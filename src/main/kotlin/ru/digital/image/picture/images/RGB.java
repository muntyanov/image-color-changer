package ru.digital.image.picture.images;

public class RGB {
    int red, green, blue;

    public RGB(int rgb) {
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = rgb & 0xFF;
    }

    public RGB(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public int toInt(){
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    public int toInverseInt(){
        int rgb = 255-red;
        rgb = (rgb << 8) +255- green;
        rgb = (rgb << 8) +255- blue;
        return rgb;
    }
    public int toGrey(){
        return (red+green+blue)/3;
    }
    public double mse(RGB rbg){
        return Metric.MSE(
                red, rbg.red,
                green, rbg.green,
                blue, rbg.blue
        );
    }
    public double max(RGB rbg){
        return Metric.MAX(
                red, rbg.red,
                green, rbg.green,
                blue, rbg.blue
        );
    }
}
