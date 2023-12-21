package day_21;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Point;

public class StepCounter {

    public static long gardenPlotsReached(List<String> fileContents) {
        Set<Point> obstacles = new HashSet<>();
        Point startingPoint = new Point(0, 0);

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                char sym = line.charAt(row);

                if (sym == '#') {
                    obstacles.add(new Point(row, col));
                } else if (sym == 'S') {
                    startingPoint = new Point(row, col);
                }
            }
        }

        int maxRow = fileContents.getFirst().length() - 1;
        int maxCol = fileContents.size() - 1;

        Set<Point> current = new HashSet<>();
        current.add(startingPoint);

        Set<Point> next = new HashSet<>();

        for (int i = 0; i < 64; i++) {
            for (Point point: current) {
                for (Point nextPoint: point.getAdjacentPoints()) {
                    if (nextPoint.x() < 0 || nextPoint.x() > maxRow || nextPoint.y() < 0 || nextPoint.y() > maxCol) {
                        continue;
                    }

                    if (obstacles.contains(nextPoint)) {
                        continue;
                    }

                    next.add(nextPoint);
                }
            }

            current = next;
            next = new HashSet<>();
        }

        return current.size();
    }

    public static long gardenPlotsReachedExactSteps(List<String> fileContents) {
        Set<Point> obstacles = new HashSet<>();
        Point startingPoint = new Point(0, 0);

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                char sym = line.charAt(row);

                if (sym == '#') {
                    obstacles.add(new Point(row, col));
                } else if (sym == 'S') {
                    startingPoint = new Point(row, col);
                }
            }
        }

        var maxRow = fileContents.getFirst().length();
        var maxCol = fileContents.size();

        Set<Point> current = new HashSet<>();
        current.add(startingPoint);

        Set<Point> next = new HashSet<>();

        long last = 0;
        long increase = 0;
        long lastDiff = 0;
        long step = 0;

        for (int i = 1; i <= 26501365; i++) {
            for (Point point: current) {
                for (var nextPoint: point.getAdjacentPoints()) {
                    var x = (((nextPoint.x() % maxRow) + maxRow) % maxRow);
                    var y = (((nextPoint.y() % maxCol) + maxCol) % maxCol);

                    if (obstacles.contains(new Point(x, y))) {
                        continue;
                    }

                    next.add(nextPoint);
                }
            }

            current = next;
            next = new HashSet<>();

            if (i % 131 == 65) {
                var countIncrease = current.size() - last;
                var increaseDifference = countIncrease - increase;
                var increaseDifferenceDifference = increaseDifference - lastDiff;

                last = current.size();
                increase = countIncrease;
                lastDiff = increaseDifference;

                if (increaseDifferenceDifference == 0) {
                    step = i;
                    break;
                }
            }
        }

        while (step < 26501365) {
            increase += lastDiff;
            last += increase;
            step += 131;
        }

        return last;
    }
}
