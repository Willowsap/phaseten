package phaseten.phases;
/**
 * ActiveRankComparator.java
 */

import java.util.Comparator;

import phaseten.Card;

/**
 * Compares two cards based on their active rank.
 * For normal cards active rank is the same as rank.
 * For wild cards, active rank is the rank the wild is being used as.
 * 
 * This is used to sort lists of cards by rank.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class ActiveRankComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        return a.getActiveRank().compareTo(b.getActiveRank());
    }
}
