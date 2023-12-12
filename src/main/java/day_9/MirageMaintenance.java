package day_9;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import util.Regex;

public class MirageMaintenance {

    public static long extrapolatedSumPartOne(String input) {
        return Arrays.stream(input.trim().split(System.lineSeparator()))
            .map(MirageMaintenance::parseLong)
            .map(MirageMaintenance::expand)
            .map(Lists::reverse)
            .map(l -> l.stream().reduce(0L, (a, b) -> a + b.getLast(), Long::sum))
            .mapToLong(Long::longValue)
            .sum();
    }

    public static long extrapolatedSumPartTwo(String input) {
        return Arrays.stream(input.trim().split(System.lineSeparator()))
            .map(MirageMaintenance::parseLong)
            .map(MirageMaintenance::expand)
            .map(Lists::reverse)
            .map(l -> l.stream().reduce(0L, (a, b) -> b.getFirst() - a, Long::sum))
            .mapToLong(Long::longValue)
            .sum();
    }

    private static List<Long> parseLong(String input) {
        return Regex.matchAll("\\-?\\d+", input)
            .stream()
            .map(Long::parseLong)
            .toList();
    }

    private static List<List<Long>> expand(List<Long> numbers) {
        List<List<Long>> longsList = new ArrayList<>();
        longsList.add(numbers);

        List<Long> differences = numbers;

        do {
            LinkedList<Long> next = new LinkedList<>();

            for (int i = 0; i < differences.size() - 1; i++) {
                next.add(differences.get(i + 1) - differences.get(i));
            }

            longsList.add(next);
            differences = next;
        } while (differences.stream().anyMatch(e -> e != 0));

        longsList.removeLast();

        return longsList;
    }
}
