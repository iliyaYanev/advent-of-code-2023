package day_11;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import util.PointV2;

public class CosmicExpansion {

    public static long shortestPathOne(List<String> fileContents) {
        return calculateDistance(fileContents, 2);
    }

    public static long shortestPathTwo(List<String> fileContents) {
        return calculateDistance(fileContents, 1000000);
    }

    private static long calculateDistance(List<String> fileContents, int multiplier) {
        long distance = 0;
        List<PointV2> galaxies = new ArrayList<>();

        for (int y = 0; y < fileContents.size(); y++) {
            for (int x = 0; x < fileContents.get(y).length(); x++) {
                if (fileContents.get(y).charAt(x) == '#') {
                    galaxies.add(new PointV2(x, y));
                }
            }
        }

        Set<Long> horizontalPositions = galaxies.stream()
            .map(PointV2::getX)
            .collect(Collectors.toSet());

        Set<Long> verticalPositions = galaxies.stream()
            .map(PointV2::getY)
            .collect(Collectors.toSet());

        List<Long> emptyRows = LongStream.rangeClosed(0, fileContents.size() - 1)
            .filter(y -> !horizontalPositions.contains(y)).boxed()
            .toList();

        List<Long> emptyCols = LongStream.rangeClosed(0, fileContents.size() - 1)
            .filter(y -> !verticalPositions.contains(y)).boxed()
            .toList();

        for (PointV2 galaxy: galaxies) {
            long delHor = emptyRows.stream()
                .filter(emptyColumn -> galaxy.getX() > emptyColumn)
                .count();

            long delVer = emptyCols.stream()
                .filter(emptyLine -> galaxy.getY() > emptyLine)
                .count();

            PointV2 newPosition = galaxy.add(new PointV2(delHor * (multiplier - 1), delVer * (multiplier - 1)));
            galaxy.setX(newPosition.getX());
            galaxy.setY(newPosition.getY());
        }


        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i; j < galaxies.size(); j++) {
                distance += galaxies.get(i).manhattanDistance(galaxies.get(j));
            }
        }

        return distance;
    }
}
