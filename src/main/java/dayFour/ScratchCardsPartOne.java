package dayFour;

import java.util.Arrays;
import java.util.List;

public class ScratchCardsPartOne {

    public static long scratchCardPoints(List<String> fileContents) {
        long points = 0;

        for (String line : fileContents) {
            String[] cards = line.split(":\\s+")[1]
                .split("\\|");

            List<Integer> winningCards = Arrays.stream(cards[0].trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            List<Integer> playerCards = Arrays.stream(cards[1].trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            points += (long) Math.pow(2, matchingElementsCount(playerCards, winningCards) - 1);
        }

        return points;
    }

    private static long matchingElementsCount(List<Integer> winningCards, List<Integer> playerCards) {
        return winningCards.stream()
            .filter(playerCards::contains)
            .count();
    }
}
