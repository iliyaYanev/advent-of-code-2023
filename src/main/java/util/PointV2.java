package util;

public class PointV2 {

    private long x;

    private long y;

    public PointV2(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public PointV2 add(PointV2 delta) {
        return new PointV2(x + delta.getX(), y + delta.getY());
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long manhattanDistance(PointV2 point) {
        return PointV2.manhattanDistance(this, point);
    }

    public static long manhattanDistance(PointV2 a, PointV2 b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
