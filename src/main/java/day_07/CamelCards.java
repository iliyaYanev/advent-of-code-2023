package day_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Card;
import util.Hand;

public class CamelCards {

    public static long winnings(List<String> fileContents, boolean withJokers) {
        long winnings = 0;
        List<Hand> hands = new ArrayList<>();

        for (String line : fileContents) {
            List<Card> cards = new ArrayList<>();

            String[] input = line.split(" ");
            String cardsString = input[0];
            long bid = Long.parseLong(input[1]);

            for (int i = 0; i < cardsString.length(); i++) {
                if (withJokers) {
                    char currentCard = cardsString.charAt(i) == 'J' ?
                        '*' : cardsString.charAt(i);

                    cards.add(Card.ofChar(currentCard));
                }
                else {
                    cards.add(Card.ofChar(cardsString.charAt(i)));
                }
            }

            hands.add(new Hand(cards, bid, withJokers));
        }

        Collections.sort(hands);

        for (int i = 0; i < hands.size(); i++) {
            winnings += hands.get(i).getBid() * (i + 1);
        }

        return winnings;
    }
}
