package top.guoziyang.beans;

public class Coord2d {

    private final int index;
    private final double x;
    private final double y;

    public Coord2d(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coord2d{" +
                "index=" + index +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
