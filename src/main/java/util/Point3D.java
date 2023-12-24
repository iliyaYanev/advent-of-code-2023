package util;

public class Point3D {

    private final Long x;

    private final Long y;

    public Long z;

    public Point3D(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public Point3D add(Point3D delta) {
        return new Point3D(x + delta.x, y + delta.y, z + delta.z);
    }

    public Point3D sub(Point3D delta) {
        return new Point3D(x - delta.x, y - delta.y, z - delta.z);
    }
}
