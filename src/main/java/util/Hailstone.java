package util;

public record Hailstone(long x, long y, long z, long xV, long yV, long zV) {

    public double slope() {
        return (double) yV / xV;
    }

    public boolean intersects(Hailstone other, long min, long max) {
        double slope = slope();
        double otherSlope = other.slope();

        if (slope == otherSlope) {
            return false;
        }

        double intersectX = ((otherSlope * other.x) - (slope * x) + y - other.y) / (otherSlope - slope);

        if (intersectX < min || intersectX > max) {
            return false;
        }

        double intersectY = (slope * (intersectX - x)) + y;

        if (intersectY < min || intersectY > max) {
            return false;
        }

        return isFuture(intersectX, intersectY) && other.isFuture(intersectX, intersectY);
    }

    private boolean isFuture(double x, double y) {
        return (xV >= 0 || this.x >= x) && (xV <= 0 || this.x <= x) &&
            (yV >= 0 || this.y >= y) && (yV <= 0 || this.y <= y);
    }
}
