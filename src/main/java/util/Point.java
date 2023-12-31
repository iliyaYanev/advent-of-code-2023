package util;

import java.util.List;
import java.util.Map;

public record Point(int x, int y) {

    public Point add(Point delta) {
        return new Point(x + delta.x(), y + delta.y());
    }

    public List<Point> getAdjacentPoints() {
        return List.of(
            new Point(x - 1, y),
            new Point(x + 1, y),
            new Point(x, y - 1),
            new Point(x, y + 1)
        );
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

    public static Point firstFreeSpot(Map<Point, Character> grid, Direction direction, Point point) {
        Point free = null;
        Point previousPoint = point;

        while (true) {
            previousPoint = switch (direction) {
                case NORTH -> previousPoint.north();
                case SOUTH -> previousPoint.south();
                case EAST -> previousPoint.east();
                case WEST -> previousPoint.west();
            };

            Character charAt = grid.getOrDefault(previousPoint, null);

            if (charAt == null || charAt != '.') {
                break;
            }

            free = previousPoint;
        }

        return free;
    }

    public Point forwardFromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> north();
            case SOUTH -> south();
            case WEST -> west();
            case EAST -> east();
        };
    }

    public Point leftFromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> west();
            case SOUTH -> east();
            case WEST -> south();
            case EAST -> north();
        };
    }

    public Point rightFromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> east();
            case SOUTH -> west();
            case WEST -> north();
            case EAST -> south();
        };
    }
}
