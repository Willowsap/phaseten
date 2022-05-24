package phaseten.phases;

import java.util.Comparator;

import phaseten.Card;

public class RankComparator implements Comparator<Card>{
    @Override
    public int compare(Card a, Card b) {
        return a.getRank().compareTo(b.getRank());
    }
}
