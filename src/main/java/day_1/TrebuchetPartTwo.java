package day_1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TrebuchetPartTwo {

    private static final List<String> NUMBERS = List.of(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    );

    public static Long getCalibrationValue(List<String> fileContents) {
        long calibrationValue = 0;

        for (String line: fileContents) {
            List<Integer> numbers = new LinkedList<>();

            for (int i = 0; i < line.length(); i++) {
                int index = i;
                Stream.of(line.charAt(i))
                    .filter(Character::isDigit)
                    .forEach(c -> numbers.add(Character.getNumericValue(c)));

                NUMBERS.stream()
                    .filter(e -> line.startsWith(e, index))
                    .forEach(e -> numbers.add(NUMBERS.indexOf(e) + 1));
            }

            calibrationValue += numbers.get(0) * 10 + numbers.get(numbers.size() - 1);
        }

        return calibrationValue;
    }
}
