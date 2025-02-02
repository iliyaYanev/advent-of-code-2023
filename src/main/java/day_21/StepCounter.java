package day_21;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import util.Point;

public class StepCounter {

    public static long gardenPlotsReached(List<String> fileContents) {
        Pair<Set<Point>, Point> obstaclePointPair = parseObstaclePoints(fileContents);

        Set<Point> current = getPoints(fileContents, obstaclePointPair.getRight(), obstaclePointPair.getLeft());

        return current.size();
    }

    public static long gardenPlotsReachedExactSteps(List<String> fileContents) {
        Pair<Set<Point>, Point> obstaclePointPair = parseObstaclePoints(fileContents);

        int maxRow = fileContents.getFirst().length();
        int maxCol = fileContents.size();

        Set<Point> current = new ObjectOpenHashSet<>(200000);
        current.add(obstaclePointPair.getRight());

        Set<Point> next = new ObjectOpenHashSet<>(200000);

        long last = 0;
        long increase = 0;
        long lastDiff = 0;
        long step = 0;

        for (int currentStep = 1; currentStep <= 26501365; currentStep++) {
            for (Point point: current) {
                for (Point nextPoint: point.getAdjacentPoints()) {
                    int row = Math.floorMod(nextPoint.x(), maxRow);
                    int col = Math.floorMod(nextPoint.y(), maxCol);

                    if (obstaclePointPair.getLeft().contains(new Point(row, col))) {
                        continue;
                    }

                    next.add(nextPoint);
                }
            }

            current = new ObjectOpenHashSet<>(next);
            next = new ObjectOpenHashSet<>(200000);

            if (currentStep % 131 == 65) {
                long countIncrease = current.size() - last;
                long increaseDifference = countIncrease - increase;
                long increaseDifferenceDifference = increaseDifference - lastDiff;

                last = current.size();
                increase = countIncrease;
                lastDiff = increaseDifference;

                if (increaseDifferenceDifference == 0) {
                    step = currentStep;
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

    private static Set<Point> getPoints(List<String> fileContents, Point startingPoint, Set<Point> obstacles) {
        int maxRow = fileContents.getFirst().length() - 1;
        int maxCol = fileContents.size() - 1;

        Set<Point> current = new HashSet<>();
        current.add(startingPoint);

        Set<Point> next = new HashSet<>();

        for (int row = 0; row < 64; row++) {
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

        return current;
    }

    private static Pair<Set<Point>, Point> parseObstaclePoints(List<String> fileContents) {
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

        return Pair.of(obstacles, startingPoint);
    }
}
