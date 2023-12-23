package day_08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.Ghost;
import util.MathUtil;
import util.Node;

public class Wasteland {

    public static long wastelandSteps(String input) {
        long steps = 0;

        List<String> parts = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        String moves = parts.get(0).trim();
        Map<String, Node> nodes = Node.parseNodes(parts.get(1));

        Node current = nodes.get("AAA");

        while (!current.getName().equals("ZZZ")) {
            char move = moves.charAt((int) (steps++ % moves.length()));
            current = move == 'L' ? current.getLeft() : current.getRight();
        }

        return steps;
    }

    public static long wastelandStepsZ(String input) {
        long steps = 0;
        boolean allOnZ;

        List<String> parts = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        String moves = parts.get(0).trim();
        Map<String, Node> nodes = Node.parseNodes(parts.get(1));
        LinkedList<Ghost> ghosts = new LinkedList<>();

        for (Map.Entry<String, Node> node: nodes.entrySet()) {
            if (node.getKey().endsWith("A")) {
                Ghost ghostNode = new Ghost();
                ghostNode.setPosition(node.getValue());
                ghosts.add(ghostNode);
            }
        }

        Map<Ghost, Long> ghostCycleInterval = new HashMap<>();

        do {
            char move = moves.charAt((int) (steps++ % moves.length()));
            allOnZ = true;

            for (Ghost ghost: ghosts) {
                ghost.setPosition(move == 'L' ? ghost.getPosition().getLeft() : ghost.getPosition().getRight());

                if (ghost.getPosition().getName().endsWith("Z")) {
                    if (ghost.getLastMatchIndex() != null) {
                        ghostCycleInterval.put(ghost, steps - ghost.getLastMatchIndex());
                    }

                    ghost.setLastMatchIndex(steps);
                } else {
                    allOnZ = false;
                }
            }

            if (ghostCycleInterval.size() == ghosts.size()) {
                return MathUtil.lcm(ghostCycleInterval.values());
            }
        } while (!allOnZ);

        return steps;
    }
}
