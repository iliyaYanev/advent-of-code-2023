package day_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Card;
import util.Hand;

public class CamelCards {

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

            hands.add(new Hand(cards, bid, false));
        }

        Collections.sort(hands);

        for (int i = 0; i < hands.size(); i++) {
            winnings += hands.get(i).getBid() * (i + 1);
        }

        return winnings;
    }

    public static long winningsJoker(List<String> fileContents) {
        long winnings = 0;
        List<Hand> hands = new ArrayList<>();

        for (String line : fileContents) {
            List<Card> cards = new ArrayList<>();

            String[] input = line.split(" ");
            String cardsString = input[0];
            long bid = Long.parseLong(input[1]);

            for (int i = 0; i < cardsString.length(); i++) {
                char currentCard = cardsString.charAt(i) == 'J' ?
                    '*' : cardsString.charAt(i);

                cards.add(Card.ofChar(currentCard));
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
