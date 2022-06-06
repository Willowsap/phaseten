package phaseten.phases;
/**
 * Flush.java
 */

import java.util.ArrayList;

import phaseten.Card;
import phaseten.Color;

/**
 * A card group that requires some number of cards of the same color.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class Flush extends CardGroup {

    /**
     * The color of the flush once completed.
     * Is null until completed.
     */
    private Color colorOfFlush;

    /**
     * Constructs a flush.
     * 
     * @param flushSize - the number of cards needed to complete the flush.
     */
    public Flush(int flushSize) {
        super(flushSize);
        this.colorOfFlush = null;
    }

    /**
     * Getter for the color of the flush.
     * This will be null until the flush is completed.
     * 
     * @return the color of the flush.
     */
    public Color getColorOfFlush() {
        return this.colorOfFlush;
    }

    /**
     * Checks that all cards are the same color and that
     * there are enough cards to satisfy the group size.
     */
    @Override
    public boolean isValidComplete(ArrayList<Card> cards) {
        Color color = cards.get(0).getActiveColor();
        if (color == Color.BLACK) return false;
        int validCards = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getActiveColor() == color) validCards++;
            else return false;
        }
        return validCards >= groupSize;
    }

    /**
     * Saves the color of the flush when the flush is completed.
     */
    @Override
    public void postComplete() {
        colorOfFlush = cardsInGroup.get(0).getActiveColor();
    }

    /**
     * Checks that the card is the same color as the rest of the flush.
     */
    @Override
    public boolean isValidAdd(Card card) {
        return card.getColor() == this.colorOfFlush;
    }

    @Override
    public CardGroup clone() {
        Flush t = new Flush(this.groupSize);
        super.baseClone(t);
        t.colorOfFlush = this.colorOfFlush;
        return t;
    }

    @Override
    public String getDescription() {
        return String.format("Flush of %d", this.groupSize);
    }
    
    @Override
    public String toString() {
        return String.format("Flush of %d\nCurrent Flush Color: %s",
            this.groupSize, this.colorOfFlush == null ? "none" : this.colorOfFlush);
    }
}

