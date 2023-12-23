package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;

    private String leftName;

    private String rightName;

    private Node left;

    private Node right;

    private Point point;

    private Map<Node, Integer> nodes;

    public Node(String name, String leftName, String rightName) {
        this.name = name;
        this.leftName = leftName;
        this.rightName = rightName;
    }

    public Node(Point point) {
        this.point = point;
        this.nodes = new HashMap<>();
    }

    public static Map<String, Node> parseNodes(String input) {
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

    public String getName() {
        return name;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Point getPoint() {
        return point;
    }

    public Map<Node, Integer> getNodes() {
        return nodes;
    }
}
