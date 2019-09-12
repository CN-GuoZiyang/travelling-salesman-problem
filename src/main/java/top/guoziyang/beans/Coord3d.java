package top.guoziyang.beans;

/**
 * data type of 3d coordinate
 *
 * @author Guo Ziyang
 */
public class Coord3d {

    private final int index;
    private final double x;
    private final double y;
    private final double z;

    public Coord3d(int index, double x, double y, double z) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.z = z;
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

    public double getZ() {
        return z;
    }
}
