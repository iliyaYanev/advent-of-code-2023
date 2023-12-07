package day_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Card;
import util.Hand;

public class CamelCardsPartTwo {

    public static long winnings(List<String> fileContents) {
        long winnings = 0;
        List<Hand> hands = new ArrayList<>();

        for (String line : fileContents) {
            List<Card> cards = new ArrayList<>();

            String[] input = line.split(" ");
            String cardsString = input[0];
            long bid = Long.parseLong(input[1]);

            for (int i = 0; i < cardsString.length(); i++) {
                cards.add(Card.ofChar(cardsString.charAt(i)));
            }

            hands.add(new Hand(cards, bid, true));
        }

        Collections.sort(hands);

        for (int i = 0; i < hands.size(); i++) {
            winnings += hands.get(i).getBid() * (i + 1);
        }

        return winnings;
    }
}
