package day_05;

import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import util.FertilizerMap;

public class Fertilizer {

    public static long lowestLocation(String input) {
        long minLocation = Long.MAX_VALUE;
        String[] categories = input.trim().split(System.lineSeparator() + System.lineSeparator());

        Pair<List<Long>, List<List<FertilizerMap>>> seedFertilizerPair = parseSeedsAndCategories(categories);

        for (long seed: seedFertilizerPair.getLeft()) {
            long currentValue = getCurrentValue(seed, seedFertilizerPair.getRight());

            minLocation = Math.min(currentValue, minLocation);
        }

        return minLocation;
    }

    public static long lowestLocationRanges(String input) {
        long minLocation = Long.MAX_VALUE;
        String[] categories = input.trim().split(System.lineSeparator() + System.lineSeparator());

        Pair<List<Long>, List<List<FertilizerMap>>> seedFertilizerPair = parseSeedsAndCategories(categories);

        for (int i = 0; i < seedFertilizerPair.getLeft().size(); i += 2) {
            Range<Long> seedRange = Range.open(seedFertilizerPair.getLeft().get(i),
                seedFertilizerPair.getLeft().get(i) + seedFertilizerPair.getLeft().get(i + 1));

            List<Range<Long>> valueRanges = List.of(seedRange);

            for (List<FertilizerMap> categoryFertilizerMaps : seedFertilizerPair.getRight()) {
                valueRanges = applyCategoryMaps(valueRanges, categoryFertilizerMaps);
            }

            for (Range<Long> valueRange: valueRanges) {
                if (valueRange.lowerEndpoint() < minLocation) {
                    minLocation = valueRange.lowerEndpoint();
                }
            }
        }

        return minLocation;
    }

    private static Pair<List<Long>, List<List<FertilizerMap>>> parseSeedsAndCategories(String[] categories) {
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

        return Pair.of(seeds, categoriesMaps);
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

    private static List<Range<Long>> applyCategoryMaps(List<Range<Long>> ranges, List<FertilizerMap> categoryFertilizerMaps) {
        List<Range<Long>> unmappedRanges = List.copyOf(ranges);
        List<Range<Long>> mappedRanges = new ArrayList<>();

        for (FertilizerMap fertilizerMap : categoryFertilizerMaps) {
            List<Range<Long>> newUnmappedRanges = new ArrayList<>();

            for (Range<Long> unmappedRange: unmappedRanges) {
                Range<Long> mapRange = Range.open(
                    fertilizerMap.source(), fertilizerMap.source() + fertilizerMap.count());

                if (!unmappedRange.isConnected(mapRange)) {
                    newUnmappedRanges.add(unmappedRange);
                    continue;
                }

                Range<Long> overlapping = unmappedRange.intersection(mapRange);

                // Overlapping range is mapped to the destination range.
                mappedRanges.add(Range.closed(
                    fertilizerMap.destination() + (overlapping.lowerEndpoint() - fertilizerMap.source()),
                    fertilizerMap.destination() + (overlapping.upperEndpoint() - fertilizerMap.source())
                ));

                // If there is a range before the overlapping range, add it to the unmapped ranges.
                if (unmappedRange.lowerEndpoint() < overlapping.lowerEndpoint()) {
                    newUnmappedRanges.add(Range.open(unmappedRange.lowerEndpoint(), overlapping.lowerEndpoint()));
                }

                // If there is a range after the overlapping range, add it to the unmapped ranges.
                if (unmappedRange.upperEndpoint() > overlapping.upperEndpoint()) {
                    newUnmappedRanges.add(Range.openClosed(overlapping.upperEndpoint(), unmappedRange.upperEndpoint()));
                }
            }

            unmappedRanges = newUnmappedRanges;
        }

        mappedRanges.addAll(unmappedRanges);

        return mappedRanges;
    }
}
