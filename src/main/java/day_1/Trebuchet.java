package day_1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Trebuchet {

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

    public static Long calibrationValue(List<String> fileContents) {
        long calibrationValue = 0;

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

            calibrationValue += first * 10L + last;
        }

        return calibrationValue;
    }

    public static long calibrationValueTwo(List<String> fileContents) {
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

            calibrationValue += numbers.getFirst() * 10 + numbers.getLast();
        }

        return calibrationValue;
    }
}
