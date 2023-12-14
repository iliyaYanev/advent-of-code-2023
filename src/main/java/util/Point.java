package util;

import java.util.List;

public record Point(int x, int y) {

    public Point add(Point delta) {
        return new Point(x + delta.x(), y + delta.y());
    }

    public List<Point> surroundingPoints() {
        return List.of(
            new Point(x - 1, y - 1),
            new Point(x, y - 1),
            new Point(x + 1, y - 1),
            new Point(x - 1, y),
            new Point(x + 1, y),
            new Point(x - 1, y + 1),
            new Point(x, y + 1),
            new Point(x + 1, y + 1)
        );
    }

    public Point north() {
        return new Point(x, y - 1);
    }

    public Point east() {
        return new Point(x + 1, y);
    }

    public Point south() {
        return new Point(x, y + 1);
    }

    public Point west() {
        return new Point(x - 1, y);
    }
}
