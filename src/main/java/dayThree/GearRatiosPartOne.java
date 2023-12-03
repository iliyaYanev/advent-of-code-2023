package dayThree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Point;

public class GearRatiosPartOne {

    public static int partNumbers(List<String> fileContents) {
        int partsSum = 0;
        char[][] lines = new char[fileContents.size()][];
        Set<Point> symbol = new HashSet<>();

        for (int row = 0; row < fileContents.size(); row++) {
            lines[row] = fileContents.get(row).toCharArray();
        }

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

        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length; col++) {
                Point point = new Point(col, row);
                char charAt = lines[row][col];

                if (Character.isDigit(charAt)) {
                    surrounding.addAll(point.surroundingPoints());
                    digits = digits + charAt;
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
}