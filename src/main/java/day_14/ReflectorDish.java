package day_14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Direction;
import util.Point;

public class ReflectorDish {

    public static long northSupportBeamsLoad(List<String> fileContents) {
        long totalLoad = 0;
        int maxWeight = fileContents.size();
        Map<Point, Character> grid = new HashMap<>();

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                grid.put(new Point(row, col), line.charAt(row));
            }
        }

        for (int row = 0; row < fileContents.getFirst().length(); row++) {
            for (int col = 0; col < fileContents.size(); col++) {
                Point point = new Point(row, col);
                char charAt = grid.get(point);

                if (charAt == 'O') {
                    Point lastFree = Point.firstFreeSpot(grid, Direction.NORTH, point);

                    if (lastFree != null) {
                        grid.put(lastFree, 'O');
                        grid.put(point, '.');
                    }
                }
            }
        }

        for (Map.Entry<Point, Character> entry: grid.entrySet()) {
            if (entry.getValue() == 'O') {
                totalLoad += maxWeight - entry.getKey().y();
            }
        }

        return totalLoad;
    }

    public static long northSupportBeamsLoadSpinCycles(List<String> fileContents) {
        long totalLoad = 0;
        int maxWeight = fileContents.size();
        boolean withMemory = true;

        Map<Point, Character> grid = new HashMap<>();
        Map<HashMap<Point, Character>, Integer> memory = new HashMap<>();

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                grid.put(new Point(row, col), line.charAt(row));
            }
        }

        for (int cycle = 1; cycle <= 1000000000; cycle++) {
            for (int row = 0; row < fileContents.getFirst().length(); row++) {
                for (int col = 0; col < fileContents.size(); col++) {
                    Point point = new Point(row, col);
                    Character charAt = grid.get(point);

                    if (charAt == 'O') {
                        Point lastFree = Point.firstFreeSpot(grid, Direction.NORTH, point);

                        if (lastFree != null) {
                            grid.put(lastFree, 'O');
                            grid.put(point, '.');
                        }
                    }
                }
            }

            for (int col = 0; col < fileContents.size(); col++) {
                for (int row = 0; row < fileContents.getFirst().length(); row++) {
                    Point point = new Point(row, col);
                    Character charAt = grid.get(point);

                    if (charAt == 'O') {
                        Point lastFree = Point.firstFreeSpot(grid, Direction.WEST, point);

                        if (lastFree != null) {
                            grid.put(lastFree, 'O');
                            grid.put(point, '.');
                        }
                    }
                }
            }

            for (int row = 0; row < fileContents.getFirst().length(); row++) {
                for (int col = fileContents.size() - 1; col >= 0; col--) {
                    Point point = new Point(row, col);
                    Character charAt = grid.get(point);

                    if (charAt == 'O') {
                        Point lastFree = Point.firstFreeSpot(grid, Direction.SOUTH, point);

                        if (lastFree != null) {
                            grid.put(lastFree, 'O');
                            grid.put(point, '.');
                        }
                    }
                }
            }

            for (int col = 0; col < fileContents.size(); col++) {
                for (int row = fileContents.getFirst().length() - 1; row >= 0; row--) {
                    Point point = new Point(row, col);
                    Character charAt = grid.get(point);

                    if (charAt == 'O') {
                        Point lastFree = Point.firstFreeSpot(grid, Direction.EAST, point);

                        if (lastFree != null) {
                            grid.put(lastFree, 'O');
                            grid.put(point, '.');
                        }
                    }
                }
            }

            if (withMemory) {
                if (memory.containsKey(grid)) {
                    int firstSeenIndex = memory.get(grid);
                    int repeatingWindow = cycle - firstSeenIndex;

                    cycle = 1000000000 - ((1000000000 - firstSeenIndex) % repeatingWindow);
                    withMemory = false;
                } else {
                    memory.put(new HashMap<>(grid), cycle);
                }
            }
        }


        for (Map.Entry<Point, Character> entry: grid.entrySet()) {
            if (entry.getValue() == 'O') {
                totalLoad += maxWeight - entry.getKey().y();
            }
        }

        return totalLoad;
    }
}
