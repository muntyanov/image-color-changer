package ru.digital.image.picture.images;

import java.util.Arrays;

public class Metric {
    public static double MSE(int r1, int g1, int b1, int r2, int g2, int b2) {
        return Math.sqrt(
                (r1 - r2) * (r1 - r2) +
                        (g1 - g2) * (g1 - g2) +
                        (b1 - b2) * (b1 - b2)
        );
    }

    public static double MAX(int r1, int g1, int b1, int r2, int g2, int b2) {
        return Arrays.stream(
                new double[]{
                        Math.abs(r1 - r2) * 1.,
                        Math.abs(g1 - g2) * 1.,
                        Math.abs(b1 - b2) * 1.
                }).max().getAsDouble();

    }


}
