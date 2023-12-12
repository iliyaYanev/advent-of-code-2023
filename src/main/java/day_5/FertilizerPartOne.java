package day_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.FertilizerMap;

public class FertilizerPartOne {

    public static long lowestLocation(String input) {
        long minLocation = Long.MAX_VALUE;
        String[] categories = input.trim().split(System.lineSeparator() + System.lineSeparator());

        List<Long> seeds = Arrays.stream(categories[0]
                .split("seeds: ")[1]
                .split("\\s+"))
            .map(Long::parseLong)
            .toList();

        List<List<FertilizerMap>> categoriesMaps = Arrays.stream(categories)
            .skip(1)
            .map(category -> category.lines()
                .skip(1)
                .map(entry -> {
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(entry);

                    List<Long> matches = new ArrayList<>();

                    while (m.find()) {
                        matches.add(Long.parseLong(m.group()));
                    }

                    return matches;
                })
                .map(parts -> new FertilizerMap(parts.getFirst(), parts.get(1), parts.get(2)))
                .toList())
            .toList();

        for (long seed: seeds) {
            long currentValue = getCurrentValue(seed, categoriesMaps);

            minLocation = Math.min(currentValue, minLocation);
        }

        return minLocation;
    }

    private static long getCurrentValue(long seed, List<List<FertilizerMap>> categoriesMaps) {
        long currentValue = seed;

        for (List<FertilizerMap> categoryFertilizerMaps : categoriesMaps) {
            for (FertilizerMap fertilizerMap : categoryFertilizerMaps) {
                if (currentValue >= fertilizerMap.source() && currentValue <= fertilizerMap.source() + fertilizerMap.count()) {
                    currentValue = fertilizerMap.destination() + (currentValue - fertilizerMap.source());
                    break;
                }
            }
        }

        return currentValue;
    }
}
