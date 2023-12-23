package day_23;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.Node;
import util.NodePath;
import util.Point;
import util.Steps;

public class LongWalk {

    public static long longestHike(Map<Point, Character> grid) {
        long longest = 0;

        Point startPoint = grid.keySet().stream().min(Comparator.comparingInt(Point::y)).orElseThrow();
        Point endPoint = grid.keySet().stream().max(Comparator.comparingInt(Point::y)).orElseThrow();

        LinkedList<Steps> stepsQueue = new LinkedList<>(List.of(
            new Steps(startPoint, 0, new HashSet<>()))
        );

        while (!stepsQueue.isEmpty()) {
            Steps path = stepsQueue.poll();

            if (path.point().equals(endPoint)) {
                longest = Math.max(longest, path.steps());
                continue;
            }

            path.visited().add(path.point());

            (switch (grid.get(path.point())) {
                case '>' -> List.of(path.point().east());
                case '<' -> List.of(path.point().west());
                case 'v' -> List.of(path.point().south());
                case '^' -> List.of(path.point().north());
                default -> path.point().getAdjacentPoints();
            }).stream()
                .filter(grid::containsKey)
                .filter(nextPoint -> !path.visited().contains(nextPoint))
                .forEach(nextPoint -> stepsQueue.add(new Steps(nextPoint, path.steps() + 1, new HashSet<>(path.visited()))));
        }

        return longest;
    }

    public static long longestHikeSteps(Map<Point, Character> grid) {
        long steps = 0;

        Point startPoint = grid.keySet().stream().min(Comparator.comparingInt(Point::y)).orElseThrow();
        Point endPoint = grid.keySet().stream().max(Comparator.comparingInt(Point::y)).orElseThrow();

        Map<Point, Node> junctions = new HashMap<>(Map.of(
            startPoint, new Node(startPoint), endPoint, new Node(endPoint))
        );

        for (Map.Entry<Point, Character> entry: grid.entrySet()) {
            long adjacent = entry.getKey()
                .getAdjacentPoints()
                .stream().filter(grid::containsKey)
                .count();

            if (adjacent > 2) {
                junctions.put(entry.getKey(), new Node(entry.getKey()));
            }
        }

        for (Point point: junctions.keySet()) {
            Set<Point> visited = new HashSet<>(Set.of(point));
            LinkedList<Steps> stepsQueue = new LinkedList<>(List.of(new Steps(point, 0, visited)));

            while (!stepsQueue.isEmpty()) {
                Steps pathSteps = stepsQueue.poll();
                pathSteps.visited().add(pathSteps.point());

                for (Point nextPoint: pathSteps.point().getAdjacentPoints()) {
                    if (!grid.containsKey(nextPoint) || pathSteps.visited().contains(nextPoint)) {
                        continue;
                    }

                    if (junctions.containsKey(nextPoint)) {
                        Node previous = junctions.get(point);
                        Node next = junctions.get(nextPoint);

                        previous.getNodes().put(next, pathSteps.steps() + 1);
                        next.getNodes().put(previous, pathSteps.steps() + 1);

                        continue;
                    }

                    stepsQueue.add(new Steps(nextPoint, pathSteps.steps() + 1, new HashSet<>(pathSteps.visited())));
                }
            }
        }

        LinkedList<NodePath> stepsQueue = new LinkedList<>(List.of(
            new NodePath(junctions.get(startPoint), 0, new HashSet<>()))
        );

        while (!stepsQueue.isEmpty()) {
            NodePath paths = stepsQueue.removeLast();

            if (paths.node().getPoint().equals(endPoint)) {
                steps = Math.max(steps, paths.steps());
                continue;
            }

            paths.visited().add(paths.node());

            for (Map.Entry<Node, Integer> entry: paths.node().getNodes().entrySet()) {
                if (!paths.visited().contains(entry.getKey())) {
                    stepsQueue.add(new NodePath(entry.getKey(), paths.steps() + entry.getValue(), new HashSet<>(paths.visited())));
                }
            }
        }

        return steps;
    }
}
