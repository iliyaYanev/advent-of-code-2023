package day_17;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.apache.commons.lang3.tuple.Pair;
import util.Direction;
import util.NodeV2;
import util.Player;
import util.Point;

public class ClumsyCrucible {

    public static long minimumHeatLoss(List<String> fileContents) {
        return dijkstra(fileContents, 1, 3);
    }

    public static long minimumHearLossUltra(List<String> fileContents) {
        return dijkstra(fileContents, 4, 10);
    }

    private static long dijkstra(List<String> fileContents, int min, int max) {
        Map<Point, NodeV2> grid = new HashMap<>();

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                grid.put(new Point(row, col), new NodeV2(line.charAt(row) - '0'));
            }
        }

        Point target = new Point(fileContents.getFirst().length() - 1, fileContents.size() - 1);

        PriorityQueue<Player> queue = new PriorityQueue<>(
            Comparator.comparingInt(Player::heatLoss));

        queue.add(new Player(new Point(0, 0), Direction.EAST, 0, 0));
        queue.add(new Player(new Point(0, 0), Direction.SOUTH, 0, 0));

        while (!queue.isEmpty()) {
            Player player = queue.poll();
            NodeV2 node = grid.get(player.position());

            if (player.position().equals(target) && player.chainSteps() >= min) {
                return player.heatLoss();
            }

            if (node.getVisited().contains(Pair.of(player.direction(), player.chainSteps()))) {
                continue;
            }

            node.getVisited().add(Pair.of(player.direction(), player.chainSteps()));

            if (player.chainSteps() < max) {
                Point nextPoint = player.position().forwardFromDirection(player.direction());
                NodeV2 nextNode = grid.get(nextPoint);

                if (nextNode != null && !nextNode.getVisited()
                    .contains(Pair.of(player.direction(), player.chainSteps() + 1))) {
                    queue.add(new Player(nextPoint, player.direction(), player.chainSteps() + 1, player.heatLoss() + nextNode.getHeatLoss()));
                }
            }

            if (player.chainSteps() >= min) {
                Point rightPoint = player.position().rightFromDirection(player.direction());
                Direction rightDirection = player.direction().turnRight();
                NodeV2 rightNode = grid.get(rightPoint);

                if (rightNode != null && !rightNode.getVisited().contains(Pair.of(rightDirection, 1))) {
                    queue.add(new Player(
                        rightPoint, rightDirection, 1, player.heatLoss() + rightNode.getHeatLoss()));
                }

                Point leftPoint = player.position().leftFromDirection(player.direction());
                Direction leftDirection = player.direction().turnLeft();
                NodeV2 leftNode = grid.get(leftPoint);

                if (leftNode != null && !leftNode.getVisited().contains(Pair.of(leftDirection, 1))) {
                    queue.add(new Player(
                        leftPoint, leftDirection, 1, player.heatLoss() + leftNode.getHeatLoss()));
                }
            }
        }

        return 0;
    }
}
