package streamsGenerics;

import java.io.Serializable;

public class P_08_Shape implements Serializable {
    private final String color;
    private final double width;
    private final double height;

    public P_08_Shape(String color, double width, double height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
