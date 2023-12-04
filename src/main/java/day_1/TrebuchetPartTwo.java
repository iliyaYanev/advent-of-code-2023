package day_1;

import java.util.List;
import java.util.Map;

public class TrebuchetPartTwo {

    private static final Map<String, String> NUMBERS = Map.of(
        "one", "1",
        "two", "2",
        "three", "3",
        "four", "4",
        "five", "5",
        "six", "6",
        "seven", "7",
        "eight", "8",
        "nine", "9"
    );

    public static Integer getCalibrationValue(List<String> fileContents) {
        int sum = 0;

        for (String line : fileContents) {
            int index = 999;

            while(index == 999) {
                String first = "";

                for (Map.Entry<String, String> entry : NUMBERS.entrySet()) {
                    int pos = line.indexOf(entry.getKey());

                    if (pos != -1 && pos < index) {
                        index = line.indexOf(entry.getKey());
                        first = entry.getKey();
                    }
                }

                index = -1;

                if (NUMBERS.containsKey(first)) {
                    line = line.replaceFirst(first, NUMBERS.get(first));
                    index = 999;
                }
            }

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
