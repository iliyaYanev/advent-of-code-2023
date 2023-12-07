package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand implements Comparable<Hand> {

    private final List<Card> cards;

    private final long bid;

    private HandType handType;


    public Hand(List<Card> cards, long bid, boolean withJokers) {
        this.cards = cards;
        this.bid = bid;
        calculateHandType(withJokers);
    }

    public long getBid() {
        return bid;
    }

    @Override
    public int compareTo(Hand o) {
        if (this.handType.ordinal() > o.handType.ordinal()) {
            return 1;
        } else if (this.handType.ordinal() < o.handType.ordinal()) {
            return -1;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (this.cards.get(i).ordinal() > o.cards.get(i).ordinal()) {
                return 1;
            } else if (this.cards.get(i).ordinal() < o.cards.get(i).ordinal()) {
                return -1;
            }
        }

        return 0;
    }

    private void calculateHandType(boolean withJokers) {
        int jokers = 0;
        Map<Card, Integer> cardMap = new HashMap<>();

        for (final Card card : cards) {
            if (withJokers && card.getCard() == '*') {
                jokers++;
                continue;
            }

            cardMap.merge(card, 1, Integer::sum);
        }

        if (withJokers && cardMap.isEmpty()) {
            handType = HandType.FIVE_OF_A_KIND;
            return;
        }

        if (cardMap.size() == 1) {
            handType = HandType.FIVE_OF_A_KIND;
            return;
        }

        if (cardMap.size() == 5) {
            handType = HandType.HIGH_CARD;
            return;
        }


        List<Integer> sortedValues = new ArrayList<>(cardMap.values()
            .stream()
            .sorted(Comparator.reverseOrder())
            .toList());

        if (withJokers) {
            int topValue = sortedValues.get(0);
            topValue += jokers;
            sortedValues.set(0, topValue);
        }

        boolean possibleFullHouse = false;
        boolean possibleThreeOfAKind = false;
        boolean possibleTwoPair = false;

        for (final int value : sortedValues) {
            if (value == 4) {
                handType = HandType.FOUR_OF_A_KIND;
                return;
            }

            if (value == 3) {
                possibleFullHouse = true;
                possibleThreeOfAKind = true;
            }

            if (value == 2) {
                if (possibleFullHouse) {
                    handType = HandType.FULL_HOUSE;
                    return;
                }

                if (possibleTwoPair) {
                    handType = HandType.TWO_PAIR;
                    return;
                }
                possibleTwoPair = true;
            }
        }

        if (possibleThreeOfAKind) {
            handType = HandType.THREE_OF_A_KIND;
            return;
        }

        if (possibleTwoPair) {
            handType = HandType.ONE_PAIR;
        }
    }
}
