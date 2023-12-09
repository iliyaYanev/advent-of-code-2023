package util;

public enum Card {

    JOKER('*'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char card;

    Card(char card) {
        this.card = card;
    }

    public char getCard() {
        return card;
    }

    public static Card ofChar(char c){
        Card[] values = Card.values();
        for (Card value : values) {
            if(value.card == c){
                return value;
            }

        }

        throw new IllegalArgumentException("Card not found");
    }
}
