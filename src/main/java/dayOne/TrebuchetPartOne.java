package dayOne;

import java.util.List;

public class TrebuchetPartOne {

    public static Integer getCalibrationValue(List<String> fileContents) {
        int sum = 0;

        for (String line : fileContents) {
            int first = -1;
            int last = -1;

            for (String c : line.split("")) {
                if (Character.isDigit(c.charAt(0))) {
                    if (first == -1) {
                        first = Integer.parseInt(c);
                    }

                    last = Integer.parseInt(c);
                }
            }

            sum += first * 10 + last;
        }

        return sum;
    }
}