package day_8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.MathUtil;
import util.Regex;

public class Wasteland {

    public static long wastelandStepsPartOne(String input) {
        long steps = 0;

        List<String> parts = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        String moves = parts.get(0).trim();
        Map<String, Node> nodes = parseNodes(parts.get(1));

        Node current = nodes.get("AAA");

        while (!current.name.equals("ZZZ")) {
            char move = moves.charAt((int) (steps++ % moves.length()));
            current = move == 'L' ? current.left : current.right;
        }

        return steps;
    }

    public static long wastelandStepsPartTwo(String input) {
        long steps = 0;
        boolean allOnZ;

        List<String> parts = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        String moves = parts.get(0).trim();
        Map<String, Node> nodes = parseNodes(parts.get(1));
        LinkedList<Ghost> ghosts = new LinkedList<>();

        for (Map.Entry<String, Node> node: nodes.entrySet()) {
            if (node.getKey().endsWith("A")) {
                Ghost ghostNode = new Ghost();
                ghostNode.position = node.getValue();
                ghosts.add(ghostNode);
            }
        }

        Map<Ghost, Long> ghostCycleInterval = new HashMap<>();

        do {
            char move = moves.charAt((int) (steps++ % moves.length()));
            allOnZ = true;

            for (Ghost ghost: ghosts) {
                ghost.position = move == 'L' ? ghost.position.left : ghost.position.right;

                if (ghost.position.name.endsWith("Z")) {
                    if (ghost.lastMatchIndex != null) {
                        ghostCycleInterval.put(ghost, steps - ghost.lastMatchIndex);
                    }

                    ghost.lastMatchIndex = steps;
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

    private static Map<String, Node> parseNodes(String input) {
        Map<String, Node> nodes = new HashMap<>();

        for (String map: input.trim().split("\n")) {
            List<String> matches = Regex.matchAll("\\w+", map);
            Node node = new Node(matches.get(0), matches.get(1), matches.get(2));
            nodes.put(node.name, node);
        }

        for (Map.Entry<String, Node> nodeEntrySet: nodes.entrySet()) {
            Node node = nodeEntrySet.getValue();
            node.left = nodes.get(node.leftName);
            node.right = nodes.get(node.rightName);
        }

        return nodes;
    }

    public static class Node {
        public String name;
        public String leftName;
        public String rightName;
        public Node left;
        public Node right;

        public Node(String name, String leftName, String rightName) {
            this.name = name;
            this.leftName = leftName;
            this.rightName = rightName;
        }
    }

    public static class Ghost {
        public Node position;
        public Long lastMatchIndex = null;
    }
}
