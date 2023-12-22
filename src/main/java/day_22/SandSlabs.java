package day_22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Brick;
import util.Point3D;
import util.Regex;

public class SandSlabs {

    public static long bricksDisintegrated(List<String> fileContents) {
        long bricksDisintegrated = 0;
        List<Brick> bricks = parseBricks(fileContents);

        for (Brick brick: bricks) {
            brick.setRemoved(true);
            brick.markBricksThatWouldFall();
            bricksDisintegrated += bricks.stream().anyMatch(Brick::wouldFall) ? 0 : 1;
            bricks.forEach(b -> { b.setRemoved(false); b.setWouldFall(false); });
        }

        return bricksDisintegrated;
    }

    public static long otherBricksDisintegrated(List<String> fileContents) {
        long otherBricksDisintegrated = 0;
        List<Brick> bricks = parseBricks(fileContents);

        for (Brick brick: bricks) {
            brick.setRemoved(true);
            brick.markBricksThatWouldFall();

            otherBricksDisintegrated += bricks.stream().filter(Brick::wouldFall).count();
            bricks.forEach(b -> { b.setRemoved(false); b.setWouldFall(false); });
        }

        return otherBricksDisintegrated;
    }

    private static List<Brick> parseBricks(List<String> fileContents) {
        List<Brick> bricks = new ArrayList<>();
        Map<Integer,ArrayList<Brick>> bricksAtLevelZ = new HashMap<>();

        for (String line: fileContents) {
            List<Integer> numbers = Regex.matchAll("\\d+", line)
                .stream()
                .map(Integer::parseInt)
                .toList();

            bricks.add(new Brick(
                new Point3D(numbers.get(0), numbers.get(1), numbers.get(2)),
                new Point3D(numbers.get(3), numbers.get(4), numbers.get(5)))
            );
        }

        bricks.sort(Comparator.comparingInt(a -> a.getFirstPoint().getZ()));


        for (Brick brick: bricks) {
            Integer minZ = brick.getFirstPoint().getZ();
            Integer minPossibleZ = brick.getFirstPoint().getZ();

            first:
            for (int z = brick.getFirstPoint().getZ() - 1; z >= 1; z--) {
                for (Brick b: bricksAtLevelZ.getOrDefault(z, new ArrayList<>())) {
                    if (b.getFirstPoint().getX() <= brick.getSecondPoint().getX() &&
                        b.getSecondPoint().getX() >= brick.getFirstPoint().getX() &&
                        b.getFirstPoint().getY() <= brick.getSecondPoint().getY() &&
                        b.getSecondPoint().getY() >= brick.getFirstPoint().getY()) {

                        break first;
                    }
                }

                minPossibleZ = z;
            }

            brick.getFirstPoint().z -= minZ - minPossibleZ;
            brick.getSecondPoint().z -= minZ - minPossibleZ;

            for (int z = brick.getFirstPoint().getZ(); z <= brick.getSecondPoint().getZ(); z++) {
                if (!bricksAtLevelZ.containsKey(z)) {
                    bricksAtLevelZ.put(z, new ArrayList<>());
                }

                bricksAtLevelZ.get(z).add(brick);
            }
        }

        for (Brick brick: bricks) {
            for (Brick b: bricksAtLevelZ.getOrDefault(brick.getSecondPoint().getZ() + 1, new ArrayList<>())) {
                if (b.getFirstPoint().getX() <= brick.getSecondPoint().getX() &&
                    b.getSecondPoint().getX() >= brick.getFirstPoint().getX() &&
                    b.getFirstPoint().getY() <= brick.getSecondPoint().getY() &&
                    b.getSecondPoint().getY() >= brick.getFirstPoint().getY()) {

                    brick.getSupports().add(b);
                    b.getSupportedBy().add(brick);
                }
            }


            for (Brick b: bricksAtLevelZ.getOrDefault(brick.getFirstPoint().getZ() - 1, new ArrayList<>())) {
                if (b.getFirstPoint().getX() <= brick.getSecondPoint().getX() &&
                    b.getSecondPoint().getX() >= brick.getFirstPoint().getX() &&
                    b.getFirstPoint().getY() <= brick.getSecondPoint().getY() &&
                    b.getSecondPoint().getY() >= brick.getFirstPoint().getY()) {

                    brick.getSupportedBy().add(b);
                    b.getSupports().add(brick);
                }
            }
        }

        return bricks;
    }
}
