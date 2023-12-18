package day_18;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.Direction;
import util.Point;

public class Lavaduct {

    public static long cubicMetersLava(List<String> fileContents) {
        Map<Point, Character> grid = new HashMap<>();

        Point position = new Point(0, 0);
        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        for (String line: fileContents) {
            String[] parts = line.split(" ");

            String direction = parts[0];
            int distance = Integer.parseInt(parts[1]);

            switch (direction) {
                case "R" -> {
                    for (var x = 1; x <= distance; x++) {
                        position = position.east();
                        grid.put(position, '#');
                    }
                    maxX = Math.max(maxX, position.x());
                }
                case "L" -> {
                    for (var x = 1; x <= distance; x++) {
                        position = position.west();
                        grid.put(position, '#');
                    }
                    minX = Math.min(minX, position.x());
                }
                case "U" -> {
                    for (var x = 1; x <= distance; x++) {
                        position = position.north();
                        grid.put(position, '#');
                    }
                    minY = Math.min(minY, position.y());
                }
                case "D" -> {
                    for (var x = 1; x <= distance; x++) {
                        position = position.south();
                        grid.put(position, '#');
                    }
                    maxY = Math.max(maxY, position.y());
                }
            }
        }

        LinkedList<Point> queue = getLinkedList(grid, minX);
        Set<Point> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Point point = queue.removeFirst();

            if (visited.contains(point)) {
                continue;
            }

            visited.add(point);
            grid.put(point, '#');

            for (Point p: point.surroundingPoints()) {
                if (grid.containsKey(p) || visited.contains(p)) {
                    continue;
                }

                queue.add(p);
            }
        }

        return grid.size();
    }

    public static long cubicMetersLavaNewPlan(List<String> fileContents) {
        Point2D.Double position = new Point2D.Double(0, 0);
        List<Point2D.Double> pointsList = new ArrayList<>();

        int index = 0;
        List<Direction> directions = getDirections(fileContents);

        int xDelta = 0;
        int yDelta = 0;

        for (String line: fileContents) {
            String[] parts = line.split(" ");
            String color = parts[2].substring(1, 8);

            int distance = Integer.parseInt(color.substring(1, 6), 16);

            Direction direction = directions.get(index);
            Direction previousDirection = directions.get((((index - 1) % directions.size()) + directions.size()) % directions.size());
            Direction nextDirection = directions.get((index + 1) % directions.size());

            if (previousDirection == nextDirection) {
                if (direction == Direction.EAST || direction == Direction.WEST) {
                    xDelta = 0;
                } else if (direction == Direction.SOUTH || direction == Direction.NORTH) {
                    yDelta = 0;
                }
            } else if (direction == Direction.EAST) {
                if (previousDirection == Direction.SOUTH) {
                    xDelta = -1;
                } else if (previousDirection == Direction.NORTH) {
                    xDelta = 1;
                }
            } else if (direction == Direction.WEST) {
                if (previousDirection == Direction.SOUTH) {
                    xDelta = 1;
                } else if (previousDirection == Direction.NORTH) {
                    xDelta = -1;
                }
            } else if (direction == Direction.SOUTH) {
                if (previousDirection == Direction.EAST) {
                    yDelta = 1;
                } else if (previousDirection == Direction.WEST) {
                    yDelta = -1;
                }
            } else if (direction == Direction.NORTH) {
                if (previousDirection == Direction.EAST) {
                    yDelta = -1;
                } else if (previousDirection == Direction.WEST) {
                    yDelta = 1;
                }
            }

            Point2D.Double nextPosition = switch (directions.get(index)) {
                case EAST -> new Point2D.Double(position.x + (distance + xDelta), position.y);
                case SOUTH -> new Point2D.Double(position.x, position.y + (distance + yDelta));
                case WEST -> new Point2D.Double(position.x - (distance + xDelta), position.y);
                case NORTH -> new Point2D.Double(position.x, position.y - (distance + yDelta));
            };

            pointsList.add(index, nextPosition);

            position = nextPosition;
            index++;
        }

        Point2D.Double[] points = pointsList.toArray(new Point2D.Double[0]);

        return (long) area(points);
    }

    private static List<Direction> getDirections(List<String> fileContents) {
        List<Direction> directions = new ArrayList<>();

        for (String line: fileContents) {
            var parts = line.split(" ");
            var color = parts[2].substring(1, 8);
            var direction = switch (color.charAt(6)) {
                case '0' -> Direction.EAST;
                case '1' -> Direction.SOUTH;
                case '2' -> Direction.WEST;
                case '3' -> Direction.NORTH;
                default -> throw new RuntimeException("Unknown direction: " + color.charAt(5));
            };

            directions.add(direction);
        }
        return directions;
    }

    private static LinkedList<Point> getLinkedList(Map<Point, Character> grid, int minX) {
        int countWalls = 0;
        Point start = new Point(0, 0);

        for (Point point: start.surroundingPoints()) {
            if (grid.containsKey(point)) {
                continue;
            }


            for (int x = minX; x < point.x(); x++) {
                if (grid.containsKey(new Point(x, point.y()))) {
                    countWalls++;
                }
            }

            if (countWalls % 2 == 1) {
                start = point;
                break;
            }
        }

        Point finalStart = start;
        return new LinkedList<>(){{ add(finalStart); }};
    }

    private static double area(Point2D[] polyPoints) {
        int i, j, n = polyPoints.length;
        double area = 0;

        for (i = 0; i < n; i++) {
            j = (i + 1) % n;
            area += polyPoints[i].getX() * polyPoints[j].getY();
            area -= polyPoints[j].getX() * polyPoints[i].getY();
        }
        area /= 2.0;
        return (area < 0 ? - area : area);
    }
}
