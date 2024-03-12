package ru.digital.image.picture.images;

public class DiffPointColorGetter implements ArrayPointColorGetter {
    private boolean isColorMode = false;
    private int step = 0;

    public DiffPointColorGetter(boolean isColorMode, int step) {
        this.isColorMode = isColorMode;
        this.step = step;
    }
    public DiffPointColorGetter(){}
    @Override
    public int get(int[] c){
        RGB current = new RGB(c[0]);
        RGB up = new RGB(c[1]);
        RGB next = new RGB(c[2]);

        if(isColorMode){
            var r = Math.abs(current.red - next.red);
            if(r < step)
                r = 0;
            var g = Math.abs(current.green - next.green);
            if(g < step)
                g = 0;
            var b = Math.abs(current.blue - next.blue);
            if(b < step)
                b = 0;
            var r2 = Math.abs(current.red - up.red);
            if(r2 < step)
                r2 = 0;
            var g2 = Math.abs(current.green - up.green);
            if(g2 < step)
                g2 = 0;
            var b2 = Math.abs(current.blue - up.blue);
            if(b2 < step)
                b2 = 0;
            return new RGB(Math.max(r,r2),Math.max(g,g2),Math.max(b, b2)).toInverseInt();
        } else {
            var greyCurrent = current.toGrey();
            var greyNext = next.toGrey();
            var diff = Math.abs(greyNext - greyCurrent);
            if(diff < step)
                diff = 0;
            var greyUp = up.toGrey();
            var diff2 = Math.abs(greyUp - greyCurrent);
            if(diff2 < step)
                diff2 = 0;
            diff = Math.max(diff, diff2);
            return getColor(diff,diff,diff);
        }
    }

    protected int getColor(int r,int g, int b){
        return new RGB(r,g,b).toInverseInt();
    }
}
