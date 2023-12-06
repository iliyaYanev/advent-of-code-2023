package day_6;

import java.util.Arrays;
import java.util.List;

public class WaitForItPartOne {
    public static long possibleWins(List<String> fileContents) {
        long bestTimesProduct = 1;

        List<Long> times = Arrays.stream(fileContents.get(0)
                .split(":\\s+")[1]
                .split("\\s+"))
            .map(Long::parseLong)
            .toList();

        List<Long> records = Arrays.stream(fileContents.get(1)
                .split(":\\s+")[1]
                .split("\\s+"))
            .map(Long::parseLong)
            .toList();

        for (int i = 0; i < times.size(); i++) {
            long currentRaceTime = times.get(i);
            long waysToWin = 0;

            for (int time = 1; time < currentRaceTime; time++) {
                long distance = (currentRaceTime - time) * time;

                if (distance > records.get(i)) {
                    waysToWin++;
                }
            }

            bestTimesProduct *= waysToWin;
        }

        return bestTimesProduct;
    }
}
