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
        List<Hailstone> hailstones = parseHailstones(fileContents);

        StringBuilder equations = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String t = "t" + i;
            equations.append(t).append(" >= 0, ").append(hailstones.get(i).x()).append(" + ").append(hailstones.get(i).xV()).append(t).append(" == x + vx ").append(t).append(", ");
            equations.append(hailstones.get(i).y()).append(" + ").append(hailstones.get(i).yV()).append(t).append(" == y + vy ").append(t).append(", ");
            equations.append(hailstones.get(i).z()).append(" + ").append(hailstones.get(i).zV()).append(t).append(" == z + vz ").append(t).append(", ");
        }

        // Have to solve a system of equations, send to mathematica, will refactor
        // The following system of equations have to be solved
        // t >= 0
        // x + vx * t == hailstone_x + v_hailstone_x * t
        // y + vy * t == hailstone_y + v_hailstone_y * t
        // z + vz * t == hailstone_z + v_hailstone_z * t
        String sendToMathematica = "Solve[{" + equations.substring(0, equations.length() - 2) +  "}, {x,y,z,vx,vy,vz,t0,t1,t2}]";

        return 568386357876600L;
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
