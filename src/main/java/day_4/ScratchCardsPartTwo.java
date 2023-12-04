package day_4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScratchCardsPartTwo {

    private static final Map<Integer, Integer> cardsCollected = new HashMap<>();

    public static long scratchCardPoints(List<String> fileContents) {
        long scratchCardPoints = 0;

        for (String line: fileContents) {
            String[] input = line.split(": ");
            int cardNumber = Integer.parseInt(input[0].split("\\s+")[1]);

            cardsCollected.put(cardNumber, cardsCollected.getOrDefault(cardNumber, 0) + 1);
            String[] numberList = input[1].split(" \\| ");

            List<Integer> winningCards = Arrays.stream(numberList[0].trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            List<Integer> playerCards = Arrays.stream(numberList[1].trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            long matchedNumbersCount = matchingElementsCount(winningCards, playerCards);
            int numberOfWonCards = cardsCollected.getOrDefault(cardNumber, 1);

            for (int cardOffset = 1; cardOffset <= matchedNumbersCount; cardOffset++) {
                cardsCollected.put(cardNumber + cardOffset,
                    cardsCollected.getOrDefault(cardNumber + cardOffset, 0) + numberOfWonCards
                );
            }

            scratchCardPoints += cardsCollected.get(cardNumber);
        }

        return scratchCardPoints;
    }

    private static long matchingElementsCount(List<Integer> winningCards, List<Integer> playerCards) {
        return winningCards.stream()
            .filter(playerCards::contains)
            .count();
    }
}
