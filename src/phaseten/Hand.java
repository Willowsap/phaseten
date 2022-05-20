package phaseten;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public ArrayList<Card> getCards() {
        // should change to returning a copy of the arraylist.
        return hand;
    }
}
