package day_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import util.Direction;
import util.Position;
import util.PositionsSearch;

public class PipeMazePartOne {

    private static final Set<Character> NORTH_DIRECTION = Set.of('|', '7', 'F');

    private static final Set<Character> SOUTH_DIRECTION = Set.of('|', 'L', 'J');

    private static final Set<Character> EAST_DIRECTION = Set.of('-', 'J', '7');

    private static final Set<Character> WEST_DIRECTION = Set.of('-', 'F', 'L');




    public static long farthestPoint(List<String> fileContents) {
        long farthestPoint = 1;
        char[][] nodes = new char[fileContents.size()][fileContents.get(0).length()];

        Position startPosition = null;

        for (int i = 0; i < fileContents.size(); i++) {
            final String line = fileContents.get(i);

            for (int j = 0; j < fileContents.get(0).length(); j++) {
                nodes[i][j] = line.charAt(j);
                if (nodes[i][j] == 'S') {
                    startPosition = new Position(j, i);
                }
            }
        }

        final List<PositionsSearch> nextToSearchFor = new ArrayList<>();

        assert startPosition != null;
        if (startPosition.x() != 0 && WEST_DIRECTION.contains(nodes[startPosition.y()][startPosition.x() - 1])) {
            nextToSearchFor.add(new PositionsSearch(new Position(startPosition.x() - 1, startPosition.y()), Direction.EAST));
        }

        if (startPosition.y() != 0 && NORTH_DIRECTION.contains(nodes[startPosition.y() - 1][startPosition.x()])) {
            nextToSearchFor.add(new PositionsSearch(new Position(startPosition.x(), startPosition.y() - 1), Direction.SOUTH));
        }

        if (startPosition.x() != nodes[0].length - 1 && EAST_DIRECTION.contains(nodes[startPosition.y()][startPosition.x() + 1])) {
            nextToSearchFor.add(new PositionsSearch(new Position(startPosition.x() + 1, startPosition.y()), Direction.WEST));
        }

        if (startPosition.y() != nodes.length - 1 && SOUTH_DIRECTION.contains(nodes[startPosition.y() + 1][startPosition.x()])) {
            nextToSearchFor.add(new PositionsSearch(new Position(startPosition.x(), startPosition.y() + 1), Direction.NORTH));
        }

        while (!nextToSearchFor.get(0).currentPosition().equals(nextToSearchFor.get(1).currentPosition())) {
            farthestPoint++;

            PositionsSearch firstPossibleLocation = PositionsSearch.findNextPipeNode(nextToSearchFor.get(0), nodes);
            PositionsSearch secondPossibleLocation = PositionsSearch.findNextPipeNode(nextToSearchFor.get(1), nodes);

            nextToSearchFor.clear();
            nextToSearchFor.add(firstPossibleLocation);
            nextToSearchFor.add(secondPossibleLocation);
        }

        return farthestPoint;
    }
}
