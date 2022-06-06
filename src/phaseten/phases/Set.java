package phaseten.phases;

import java.util.ArrayList;

import phaseten.Card;
import phaseten.Rank;

public class Set extends CardGroup {

    /**
     * The rank of the set once it is completed.
     * Will be null until completed.
     */
    private Rank rankOfSet;

    /**
     * Constructs a set.
     * 
     * @param setSize - the number of cards needed to complete the set.
     */
    public Set(int setSize) {
        super(setSize);
        this.rankOfSet = null;
    }

    /**
     * Getter for the rank of the set.
     * 
     * @return rankOfSet.
     */
    public Rank getRankOfSet() {
        return this.rankOfSet;
    }

    /**
     * Checks that all cards are the same rank and that there
     * are enough cards to satisfy the set size.
     */
    @Override
    public boolean isValidComplete(ArrayList<Card> cards) {
        Rank r = cards.get(0).getActiveRank();
        if (r == Rank.SKIP) return false;
        int validCards = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getActiveRank() == r) validCards++;
            else return false;
        }
        return validCards >= groupSize;
    }

    /**
     * Checks that the card has the same rank as the set.
     */
    @Override
    public boolean isValidAdd(Card card) {
        return card.getActiveRank() == rankOfSet;
    }

    /**
     * Sets the rank of the set after it is completed.
     */
    @Override
    public void postComplete() {
        rankOfSet = cardsInGroup.get(0).getActiveRank();
    }

    @Override
    public CardGroup clone() {
        Set t = new Set(this.groupSize);
        super.baseClone(t);
        t.rankOfSet = this.rankOfSet;
        return t;
    }

    @Override
    public String getDescription() {
        return String.format("Set of %d", this.groupSize);
    }
    
    @Override
    public String toString() {
        return String.format("Set of %d\nCurrent Set Rank: %s",
            this.groupSize, this.rankOfSet == null ? "none" : this.rankOfSet);
    }
}
