package day_16;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.Direction;
import util.Point;
import util.Step;
import util.Tile;

public class Lava {

    public static long tilesEnergized(List<String> fileContents) {
        Map<Point, Tile> grid = constructTiles(fileContents);

        return energizedLevel(grid, new Point(0, 0), Direction.EAST);
    }

    public static long tilesEnergizedSecondConfig(List<String> fileContents) {
        Map<Point, Tile> grid = constructTiles(fileContents);

        long maxEnergizedLevel = 0;
        int maxRow = fileContents.getFirst().length() - 1;
        int maxCol = fileContents.size() - 1;


        for (int row = 0; row <= maxRow; row++) {
            grid.values().forEach(Tile::reset);
            maxEnergizedLevel = Math.max(energizedLevel(grid, new Point(row, 0), Direction.SOUTH), maxEnergizedLevel);

            grid.values().forEach(Tile::reset);
            maxEnergizedLevel = Math.max(energizedLevel(grid, new Point(row, maxCol), Direction.NORTH), maxEnergizedLevel);
        }

        for (int col = 0; col <= maxCol; col++) {
            grid.values().forEach(Tile::reset);
            maxEnergizedLevel = Math.max(energizedLevel(grid, new Point(0, col), Direction.EAST), maxEnergizedLevel);

            grid.values().forEach(Tile::reset);
            maxEnergizedLevel = Math.max(energizedLevel(grid, new Point(maxRow, col), Direction.WEST), maxEnergizedLevel);
        }

        return maxEnergizedLevel;
    }

    private static Map<Point, Tile> constructTiles(List<String> fileContents) {
        Map<Point, Tile> grid = new HashMap<>();

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                Tile tile = new Tile();
                tile.setPosition(new Point(row, col));
                tile.setType(line.charAt(row));
                grid.put(tile.getPosition(), tile);
            }
        }

        return grid;
    }

    private static int energizedLevel(Map<Point, Tile> grid, Point startPosition, Direction start) {
        LinkedList<Step> stepQueue = new LinkedList<>();
        stepQueue.add(new Step(startPosition, start));

        while (!stepQueue.isEmpty()) {
            Step step = stepQueue.removeFirst();

            if (!grid.containsKey(step.position())) {
                continue;
            }

            Tile tile = grid.get(step.position());
            tile.setEnergized(true);

            if (tile.getBeams().contains(step.direction())) {
                continue;
            }

            tile.getBeams().add(step.direction());

            switch (tile.getType()) {
                case '|' -> {
                    switch (step.direction()) {
                        case EAST, WEST -> {
                            stepQueue.add(new Step(step.position().north(), Direction.NORTH));
                            stepQueue.add(new Step(step.position().south(), Direction.SOUTH));
                        }
                        case NORTH -> stepQueue.add(new Step(step.position().north(), Direction.NORTH));
                        case SOUTH -> stepQueue.add(new Step(step.position().south(), Direction.SOUTH));
                    }
                }
                case '-' -> {
                    switch (step.direction()) {
                        case EAST -> stepQueue.add(new Step(step.position().east(), Direction.EAST));
                        case WEST -> stepQueue.add(new Step(step.position().west(), Direction.WEST));
                        case NORTH, SOUTH -> {
                            stepQueue.add(new Step(step.position().west(), Direction.WEST));
                            stepQueue.add(new Step(step.position().east(), Direction.EAST));
                        }
                    }
                }
                case '/' -> {
                    switch (step.direction()) {
                        case EAST -> stepQueue.add(new Step(step.position().north(), Direction.NORTH));
                        case WEST -> stepQueue.add(new Step(step.position().south(), Direction.SOUTH));
                        case NORTH -> stepQueue.add(new Step(step.position().east(), Direction.EAST));
                        case SOUTH -> stepQueue.add(new Step(step.position().west(), Direction.WEST));
                    }
                }
                case '\\' -> {
                    switch (step.direction()) {
                        case EAST -> stepQueue.add(new Step(step.position().south(), Direction.SOUTH));
                        case WEST -> stepQueue.add(new Step(step.position().north(), Direction.NORTH));
                        case NORTH -> stepQueue.add(new Step(step.position().west(), Direction.WEST));
                        case SOUTH -> stepQueue.add(new Step(step.position().east(), Direction.EAST));
                    }
                }
                case '.' -> {
                    switch (step.direction()) {
                        case EAST -> stepQueue.add(new Step(step.position().east(), Direction.EAST));
                        case WEST -> stepQueue.add(new Step(step.position().west(), Direction.WEST));
                        case NORTH -> stepQueue.add(new Step(step.position().north(), Direction.NORTH));
                        case SOUTH -> stepQueue.add(new Step(step.position().south(), Direction.SOUTH));
                    }
                }
            }
        }

        return grid.values().stream()
            .filter(Tile::isEnergized)
            .toList()
            .size();
    }
}
