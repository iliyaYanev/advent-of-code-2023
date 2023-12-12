package day_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Triple;
import util.Regex;

public class HotSprings {

    private static final Map<Triple<String, List<Integer>, Boolean>, Long> previous = new HashMap<>();

    public static long arrangementCount(List<String> fileContents) {
        long arrangementCount = 0;

        for (String line: fileContents) {
            String[] parts = line.split(" ");
            String springsLine = parts[0].chars()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());

            List<Integer> numbers = Regex.matchAll("\\d+", parts[1])
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            arrangementCount += combinationsCount(springsLine, numbers, false);
        }

        return arrangementCount;
    }

    public static long unfoldedArrangementCount(List<String> fileContents) {
        long unfoldedRecordsCount = 0;

        for (String line: fileContents) {
            String[] parts = line.split(" ");
            String springsLine = parts[0].chars()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());

            List<Integer> numbers = Regex.matchAll("\\d+", parts[1])
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            String newSpringsLine = springsLine + ('?' + springsLine).repeat(4);
            LinkedList<Integer> newNumbers = new LinkedList<>(numbers){{
                addAll(numbers); addAll(numbers); addAll(numbers); addAll(numbers);
            }};

            unfoldedRecordsCount += combinationsCount(newSpringsLine, newNumbers, false);
        }

        return unfoldedRecordsCount;
    }

    private static long combinationsCount(String line, List<Integer> damaged, boolean inGroup) {
        Triple<String, List<Integer>, Boolean> state = Triple.of(line, damaged, inGroup);
        Long cached = load(state);

        if (cached != null) {
            return cached;
        }

        if (line.isEmpty()) {
            if (damaged.isEmpty() || (damaged.size() == 1 && damaged.getFirst() == 0)) {
                return 1;
            }

            return 0;
        }

        char head = line.charAt(0);
        String remainingLine = line.substring(1);

        if (head == '.') {
            if (inGroup) {
                if (damaged.getFirst() != 0) {
                    return 0;
                }

                return savePrevious(state, combinationsCount(remainingLine, damaged.subList(1, damaged.size()), false));
            }

            return savePrevious(state, combinationsCount(remainingLine, damaged, false));
        }

        if (head == '#' && !damaged.isEmpty() && damaged.getFirst() > 0) {
            List<Integer> damagedWithDecreasedFirstCount = new ArrayList<>(damaged);
            damagedWithDecreasedFirstCount.set(0, damagedWithDecreasedFirstCount.getFirst() - 1);

            return savePrevious(state, combinationsCount(remainingLine, damagedWithDecreasedFirstCount, true));
        }

        if (head == '?') {
            return savePrevious(state,
                combinationsCount('.' + remainingLine, damaged, inGroup)
                    + combinationsCount('#' + remainingLine, damaged, inGroup)
            );
        }

        return 0;
    }

    private static long savePrevious(Triple<String, List<Integer>, Boolean> state, long result) {
        previous.put(state, result);

        return result;
    }

    public static Long load(Triple<String, List<Integer>, Boolean> state) {
        return previous.getOrDefault(state, null);
    }
}
