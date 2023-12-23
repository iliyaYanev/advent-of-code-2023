package util;

import java.util.Set;

public record Steps(Point point, int steps, Set<Point> visited) { }
