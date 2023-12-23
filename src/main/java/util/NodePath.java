package util;

import java.util.Set;

public record NodePath(Node node, int steps, Set<Node> visited) { }
