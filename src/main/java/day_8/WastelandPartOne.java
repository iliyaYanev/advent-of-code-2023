package day_8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import util.Node;

public class WastelandPartOne {

    public static long wastelandSteps(String input) {
        long steps = 0;

        List<String> parts = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        String moves = parts.get(0).trim();
        Map<String, Node> nodes = Node.parseNodes(parts.get(1));

        Node current = nodes.get("AAA");

        while (!current.name.equals("ZZZ")) {
            char move = moves.charAt((int) (steps++ % moves.length()));
            current = move == 'L' ? current.left : current.right;
        }

        return steps;
    }
}
