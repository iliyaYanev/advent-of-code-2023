package day_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.Point;

public class GearRatios {

    public static long partNumbers(List<String> fileContents) {
        long partsSum = 0;
        char[][] lines = new char[fileContents.size()][];
        Set<Point> symbol = new HashSet<>();

        // Fill in lines array with the input values
        for (int row = 0; row < fileContents.size(); row++) {
            lines[row] = fileContents.get(row).toCharArray();
        }

        // Add special symbol locations to a set
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length; col++) {
                char charAt = lines[row][col];

                if (!Character.isDigit(charAt) && charAt != '.') {
                    symbol.add(new Point(col, row));
                }
            }
        }

        Set<Point> surrounding = new HashSet<>();
        String digits = "";

        // Collect numbers from the surrounding points
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length; col++) {
                Point point = new Point(col, row);
                char charAt = lines[row][col];

                if (Character.isDigit(charAt)) {
                    surrounding.addAll(point.surroundingPoints());
                    digits += charAt;
                }
                else if (!digits.isEmpty()) {
                    for (Point s: surrounding) {
                        if (symbol.contains(s)) {
                            partsSum += Integer.parseInt(digits);
                            break;
                        }
                    }

                    surrounding.clear();
                    digits = "";
                }
            }

            if (!digits.isEmpty()) {
                for (Point s: surrounding) {
                    if (symbol.contains(s)) {
                        partsSum += Integer.parseInt(digits);
                        break;
                    }
                }

                surrounding.clear();
                digits = "";
            }
        }

        return partsSum;
    }

    public static long gearRatios(List<String> fileContents) {
        long gearRations = 0;
        Map<Point, List<Long>> starNumbers = new HashMap<>();
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
                            starNumbers.get(s).add(Long.parseLong(digits));
                        }
                    }

                    surrounding.clear();
                    digits = "";
                }
            }

            if (!digits.isEmpty()) {
                for (Point s: surrounding) {
                    if (starNumbers.containsKey(s)) {
                        starNumbers.get(s).add(Long.parseLong(digits));
                    }
                }

                surrounding.clear();
                digits = "";
            }
        }

        for (Map.Entry<Point, List<Long>> star: starNumbers.entrySet()) {
            List<Long> numbers = star.getValue();

            if (numbers.size() == 2) {
                gearRations += numbers.get(0) * numbers.get(1);
            }
        }

        return gearRations;
    }
}
