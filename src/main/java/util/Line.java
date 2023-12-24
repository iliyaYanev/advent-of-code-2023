package util;

public class Line {

    private final PointV2 point;

    private final PointV2 delta;

    public Line(PointV2 point, long deltaX, long deltaY) {
        this.point = point;
        this.delta = new PointV2(deltaX, deltaY);
    }

    public PointV2 intersects(Line line) {
        double firstLineSlope = (double) delta.getY() / delta.getX();
        double secondLineSlope = (double) line.delta.getY() / line.delta.getX();

        double first = point.getY() - firstLineSlope * point.getX();
        double second = line.point.getY() - secondLineSlope * line.point.getX();

        if (firstLineSlope == secondLineSlope) {
            return first == second ? point : null;
        }

        long intersectionX = Math.round((second - first) / (firstLineSlope - secondLineSlope));
        long intersectionY = Math.round(firstLineSlope * intersectionX + first);

        return new PointV2(intersectionX, intersectionY);
    }
}
