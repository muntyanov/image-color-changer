package ru.digital.image.picture.images;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinMaxColorOfPointGetter implements OnePointColorGetter {


    public MinMaxColorOfPointGetter(int[] defaultColors){
        this.defaultColors = Arrays.stream(defaultColors).mapToObj(
                it -> new RGB(it)
        ).collect(Collectors.toList());
    }
    @Override
    public int get(int color) {
        var current = new RGB(color);
        Object[] obj = defaultColors.stream().map(
                it ->
                        new Object[]{it, it.max(current)}
        ).min(
                (it1, it2) -> {
                    if ((double) it1[1] > (double) it2[1])
                        return 1;
                    else if ((double) it1[1] == (double) it2[1])
                        return 0;
                    else return -1;
                }
        ).get();
        return ((RGB) obj[0]).toInt();
    }

    private List<RGB> defaultColors;
}
