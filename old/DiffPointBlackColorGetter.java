package ru.digital.image.picture.images;

public class DiffPointBlackColorGetter extends DiffPointColorGetter {
    public DiffPointBlackColorGetter(boolean isColorMode, int step) {
        super(isColorMode, step);
    }

    @Override
    protected int getColor(int r, int g, int b) {
        if(r > 0){
            r = 255;
        }
        if(g > 0){
            g = 255;
        }
        if(b > 0){
            b = 255;
        }
        return super.getColor(r,g,b);
    }
}
