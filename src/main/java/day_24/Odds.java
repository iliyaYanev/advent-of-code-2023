package day_24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Hailstone;
import util.Line;
import util.Point3D;
import util.PointV2;
import util.Regex;

public class Odds {

    private static final long MIN = 200000000000000L;

    private static final long MAX = 400000000000000L;

    public static long areaIntersections(List<String> fileContents) {
        long intersections = 0;

        List<Hailstone> hailstones = populateHailstones(fileContents);

        for (int i = 0; i < hailstones.size() - 1; i++) {
            for (int j = i + 1; j < hailstones.size(); j++) {
                Hailstone first = hailstones.get(i);
                Hailstone second = hailstones.get(j);

                double firstSlope = (double) first.velocity().getY() / first.velocity().getX();
                double secondSlope = (double) second.velocity().getY() / second.velocity().getX();

                double firstBreak = first.position().getY() - firstSlope * first.position().getX();
                double secondBreak = second.position().getY() - secondSlope * second.position().getX();

                double intersectionX = (secondBreak - firstBreak) / (firstSlope - secondSlope);
                double intersectionY = firstSlope * intersectionX + firstBreak;

                if (
                    intersectionX >= MIN && intersectionX <= MAX &&
                        intersectionY >= MIN && intersectionY <= MAX &&
                        (isIntersectingX(first, intersectionX)) &&
                        (first.velocity().getY() >= 0 && intersectionY >= first.position().getY()
                            || first.velocity().getY() < 0 && intersectionY < first.position().getY()) &&
                        (isIntersectingX(second, intersectionX)) &&
                        (second.velocity().getY() >= 0 && intersectionY >= second.position().getY()
                            || second.velocity().getY() < 0 && intersectionY < second.position().getY())) {

                    intersections++;
                }
            }
        }

        return intersections;
    }

    public static long areaIntersectionsWithZ(List<String> fileContents) {
        List<Hailstone> hailstones = populateHailstones(fileContents);

        Set<Long> possibleVelocityX = new HashSet<>();
        Set<Long> possibleVelocityY = new HashSet<>();
        Set<Long> possibleVelocityZ = new HashSet<>();

        for (int i = 0; i < hailstones.size() - 1; i++) {
            for (int j = i + 1; j < hailstones.size(); j++) {
                Hailstone first = hailstones.get(i);
                Hailstone second = hailstones.get(j);

                if (first.velocity().getX().equals(second.velocity().getX())) {
                    Set<Long> possible = new HashSet<>();
                    long difference = second.position().getX() - first.position().getX();
                    for (long v = -1000; v <= 1000; v++) {
                        if (v == first.velocity().getX()) {
                            continue;
                        }

                        if (Math.floorMod(difference, v - first.velocity().getX()) == 0) {
                            possible.add(v);
                        }
                    }

                    if (possibleVelocityX.isEmpty()) {
                        possibleVelocityX.addAll(possible);
                    } else {
                        possibleVelocityX.retainAll(possible);
                    }
                }

                if (first.velocity().getY().equals(second.velocity().getY())) {
                    Set<Long> possible = new HashSet<>();
                    long difference = second.position().getY() - first.position().getY();
                    for (long v = -1000; v <= 1000; v++) {
                        if (v == first.velocity().getY()) {
                            continue;
                        }

                        if (Math.floorMod(difference, v - first.velocity().getY()) == 0) {
                            possible.add(v);
                        }
                    }

                    if (possibleVelocityY.isEmpty()) {
                        possibleVelocityY.addAll(possible);
                    } else {
                        possibleVelocityY.retainAll(possible);
                    }
                }

                if (first.velocity().z.equals(second.velocity().z)) {
                    Set<Long> possible = new HashSet<>();
                    long difference = second.position().z - first.position().z;
                    for (long v = -1000; v <= 1000; v++) {
                        if (v == first.velocity().z) {
                            continue;
                        }

                        if (Math.floorMod(difference, v - first.velocity().z) == 0) {
                            possible.add(v);
                        }
                    }

                    if (possibleVelocityZ.isEmpty()) {
                        possibleVelocityZ.addAll(possible);
                    } else {
                        possibleVelocityZ.retainAll(possible);
                    }
                }
            }
        }

        long velocityX = possibleVelocityX.stream().findFirst().orElseThrow();
        long velocityY = possibleVelocityY.stream().findFirst().orElseThrow();
        long velocityZ = possibleVelocityZ.stream().findFirst().orElseThrow();

        Hailstone first = hailstones.get(0);
        Hailstone second = hailstones.get(1);

        Point3D firstBreak = first.velocity().sub(new Point3D(velocityX, velocityY, velocityZ));
        Point3D secondBreak = second.velocity().sub(new Point3D(velocityX, velocityY, velocityZ));

        Line firstLine = new Line(new PointV2(first.position().getX(), first.position().getY()), firstBreak.getX(), firstBreak.getY());
        Line secondLine = new Line(new PointV2(second.position().getX(), second.position().getY()), secondBreak.getX(), secondBreak.getY());

        PointV2 intersection = firstLine.intersects(secondLine);
        assert intersection != null;

        long timeToReach = (intersection.getX() - first.position().getX()) / firstBreak.getX();

        long z = first.position().z + firstBreak.z * timeToReach;

        return intersection.getX() + intersection.getY() + z;
    }

    private static List<Hailstone> populateHailstones(List<String> fileContents) {
        List<Hailstone> hailstones = new ArrayList<>();

        for (String line: fileContents) {
            List<Long> numbers = Regex.matchAll("\\-?\\d+", line)
                .stream()
                .map(Long::parseLong)
                .toList();

            hailstones.add(
                new Hailstone(
                    new Point3D(numbers.get(0), numbers.get(1), numbers.get(2)),
                    new Point3D(numbers.get(3), numbers.get(4), numbers.get(5))
                )
            );
        }

        return hailstones;
    }

    private static boolean isIntersectingX(Hailstone hailstone, double intersectionX) {
        return hailstone.velocity().getX() >= 0 && intersectionX >= hailstone.position().getX()
            || hailstone.velocity().getX() < 0 && intersectionX < hailstone.position().getX();
    }
}
