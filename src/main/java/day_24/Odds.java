package day_24;

import java.util.ArrayList;
import java.util.List;
import util.Hailstone;

public class Odds {

    private static final long MIN = 200000000000000L;

    private static final long MAX = 400000000000000L;

    public static long areaIntersections(List<String> fileContents) {
        long intersections = 0;
        List<Hailstone> hailstones = parseHailstones(fileContents);

        for (int i = 0; i < hailstones.size(); i++) {
            for (int j = i + 1; j < hailstones.size(); j++) {
                if (i == j) continue;
                intersections += hailstones.get(i).intersects(hailstones.get(j), MIN, MAX) ? 1 : 0;
            }
        }

        return intersections;
    }

    public static long areaIntersectionsWithZ(List<String> fileContents) {
        long intersections = 0;
        List<Hailstone> hailstones = parseHailstones(fileContents);


        return intersections;
    }

    public static List<Hailstone> parseHailstones(List<String> fileContents) {
        List<Hailstone> hailstones = new ArrayList<>();

        fileContents.forEach(line -> {
            String[] parts = line.replaceAll("\\s", "").split("@");

            String[] positions = parts[0].split(",");
            String[] velocity = parts[1].split(",");

            hailstones.add(new Hailstone(Long.parseLong(positions[0]), Long.parseLong(positions[1]), Long.parseLong(positions[2]),
                Long.parseLong(velocity[0]), Long.parseLong(velocity[1]), Long.parseLong(velocity[2])));
        });

        return hailstones;
    }
}
