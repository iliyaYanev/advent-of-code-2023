package util;

public class Point3D {

    private final Integer x;

    private final Integer y;

    public Integer z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public Point3D add(Point3D delta) {
        return new Point3D(x + delta.x, y + delta.y, z + delta.z);
    }
}
