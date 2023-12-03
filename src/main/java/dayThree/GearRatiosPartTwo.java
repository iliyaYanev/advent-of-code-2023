package dayThree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.Point;

public class GearRatiosPartTwo {

    public static int gearRatios(List<String> fileContents) {
        int gearRations = 0;
        Map<Point, List<Integer>> starNumbers = new HashMap<>();
        char[][] lines = new char[fileContents.size()][];

        // Fill in lines array with the input values
        for (int row = 0; row < lines.length; row++) {
            lines[row] = fileContents.get(row).toCharArray();
        }

        // Add gear symbol locations to a set
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length; col++) {
                if (lines[row][col] == '*') {
                    starNumbers.put(new Point(col, row), new ArrayList<>());
                }
            }
        }

        Set<Point> surrounding = new HashSet<>();
        String digits = "";

        for (int row = 0; row < lines.length; row++) {
            char[] line = lines[row];

            for (int col = 0; col < line.length; col++) {
                Point point = new Point(col, row);
                char charAt = line[col];

                if (Character.isDigit(charAt)) {
                    surrounding.addAll(point.surroundingPoints());
                    digits += charAt;
                }
                else if (!digits.isEmpty()) {
                    for (Point s : surrounding) {
                        if (starNumbers.containsKey(s)) {
                            starNumbers.get(s).add(Integer.parseInt(digits));
                        }
                    }

                    surrounding.clear();
                    digits = "";
                }
            }

            if (!digits.isEmpty()) {
                for (Point s: surrounding) {
                    if (starNumbers.containsKey(s)) {
                        starNumbers.get(s).add(Integer.parseInt(digits));
                    }
                }

                surrounding.clear();
                digits = "";
            }
        }

        for (Map.Entry<Point, List<Integer>> star: starNumbers.entrySet()) {
            List<Integer> numbers = star.getValue();

            if (numbers.size() == 2) {
                gearRations += numbers.get(0) * numbers.get(1);
            }
        }

        return gearRations;
    }
}
