package day_25;

import java.util.List;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import util.Regex;

public class Snowverload {

    public static long multiplyGroups(List<String> fileContents) {
        var graph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        for (String line: fileContents) {
            Regex.matchAll("\\w+", line)
                .stream()
                .filter(s -> !graph.containsVertex(s)).forEach(graph::addVertex);
        }

        for (String line: fileContents) {
            List<String> connections = Regex.matchAll("\\w+", line);
            String first = connections.removeFirst();

            connections.stream()
                .filter(s -> !graph.containsEdge(first, s)).forEach(s -> graph.addEdge(first, s));
        }

        StoerWagnerMinimumCut<String, DefaultEdge> minimumCut = new StoerWagnerMinimumCut<>(graph);
        int size = minimumCut.minCut().size();

        return (long) size * (graph.vertexSet().size() - size);
    }
}
