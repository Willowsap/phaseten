package phaseten.phases;

import java.util.ArrayList;
import java.util.Collections;

import phaseten.Card;
import phaseten.Rank;

public class Run extends CardGroup {

    /**
     * The current upper-end of the run.
     * Will be null until completed.
     */
    private Rank upperRank;

    /**
     * The current lower-end of the run.
     * Will be null until completed.
     */
    private Rank lowerRank;

    /**
     * Constructs a run.
     * 
     * @param runSize - the number of cards needed to complete the run.
     */
    public Run(int runSize) {
        super(runSize);
        this.upperRank = null;
        this.lowerRank = null;
    }

    /**
     * Getter for the upper rank of the run.
     * 
     * @return - upperRank
     */
    public Rank getUpperRank() {
        return this.upperRank;
    }

    /**
     * Getter for the lower rank of the run.
     * 
     * @return - lowerRank
     */
    public Rank getLowerRank() {
        return this.lowerRank;
    }

    /**
     * Checks that the cards are in sequential order by active rank.
     */
    @Override
    public boolean isValidComplete(ArrayList<Card> cards) {
        Collections.sort(cards, new ActiveRankComparator());
        if (cards.get(0).getActiveRank() == Rank.SKIP) return false;
	    int startingIndex = cards.get(0).getRank().ordinal();
        Rank r = cards.get(1).getRank();
        for (int i = 1; i < cards.size(); i++, r = cards.get(i).getRank())
            if (r.ordinal() != startingIndex + i || r == Rank.SKIP)
                return false;
        return true;
    }

    /**
     * Checks that the provided card can go at the top or bottom of the run.
     */
    @Override
    public boolean isValidAdd(Card card) {
        return (upperRank != Rank.TWELVE
                && card.getRank().ordinal() == upperRank.ordinal() + 1)
            || (lowerRank != Rank.ONE
                && card.getRank().ordinal() == lowerRank.ordinal() - 1);
    }

    /**
     * Saves the high end and low end ranks of the run.
     */
    @Override
    public void postComplete() {
        upperRank = cardsInGroup.get(cardsInGroup.size() - 1).getActiveRank();
        lowerRank = cardsInGroup.get(0).getActiveRank();
    }

    /**
     * Re-sorts the list of cards by rank and updates upper and lower ranks.
     */
    @Override
    public void postAdd() {
        Collections.sort(cardsInGroup, new ActiveRankComparator());
        postComplete();
    }

    @Override
    public CardGroup clone() {
        Run t = new Run(this.groupSize);
        super.baseClone(t);
        t.upperRank = this.upperRank;
        t.lowerRank = this.lowerRank;
        return t;
    }

    @Override
    public String getDescription() {
        return String.format("Run of %d", this.groupSize);
    }

    @Override
    public String toString() {
        String desc = "Run of " + this.groupSize + "\nCurrent run: ";
        for (Card c : this.cardsInGroup) desc += c.getRank() + " ";
        return desc;
    }
}
