package day_6;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;

public class WaitForIt {
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

    public static long possibleWinsLongerRace(List<String> fileContents) {
        long waysToWin = 0;

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

        long totalTime = Long.parseLong(Joiner.on("").join(times));
        long totalRecord = Long.parseLong(Joiner.on("").join(records));

        for (long currentTime = 1; currentTime < totalTime; currentTime++) {
            long distance = (totalTime - currentTime) * currentTime;

            if (distance > totalRecord) {
                waysToWin++;
            } else if (distance < totalRecord && waysToWin > 0) {
                break;
            }
        }

        return waysToWin;
    }
}
