package util;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class NodeV2 {

    private final int heatLoss;

    private final Set<Pair<Direction, Integer>> visited;

    public NodeV2(int heatLoss) {
        this.heatLoss = heatLoss;
        this.visited = new HashSet<>();
    }

    public int getHeatLoss() {
        return heatLoss;
    }

    public Set<Pair<Direction, Integer>> getVisited() {
        return visited;
    }
}
