package day_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class PointsOfIncidence {

    public static long reflectionPatterns(String input) {
        return mirroredImages(input, 0);
    }

    public static long reflectionPatternsReflectionLine(String input) {
        return mirroredImages(input, 1);
    }

    private static long mirroredImages(String input, int offset) {
        long patternSum = 0;

        List<String> patterns = Arrays.stream(input.trim().split(System.lineSeparator() + System.lineSeparator()))
            .toList();

        for (String pattern: patterns) {
            List<String> lines = Arrays.stream(pattern.split(System.lineSeparator()))
                .toList();

            List<List<Character>> columnsList = new ArrayList<>();

            for (String line: lines) {
                for (int index = 0; index < line.length(); index++) {
                    if (columnsList.size() < index + 1) {
                        columnsList.add(new ArrayList<>());
                    }
                    columnsList.get(index).add(line.charAt(index));
                }
            }

            List<String> columns = columnsList.stream()
                .map(column -> column.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining()))
                .toList();

            for (int row = 0; row < lines.size() - 1; row++) {
                long rowDistance = 0;

                rowDistance += calculateDistance(lines, offset, row, rowDistance);

                if (rowDistance == offset) {
                    patternSum += 100L * (row + 1);
                    break;
                }
            }

            for (int col = 0; col < columns.size() - 1; col++) {
                long colDistance = 0;

                colDistance += calculateDistance(columns, offset, col, colDistance);

                if (colDistance == offset) {
                    patternSum += col + 1;
                    break;
                }
            }
        }

        return patternSum;
    }

    private static long calculateDistance(List<String> lines, int offset, int index, long distance) {
        LevenshteinDistance levenshteinDistance = LevenshteinDistance.getDefaultInstance();

        for (int j = index, k = index + 1; j >= 0 && k < lines.size() && distance <= offset; j--, k++) {
            distance += levenshteinDistance.apply(lines.get(j), lines.get(k));
        }

        return distance;
    }
}
