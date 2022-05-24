package phaseten.phases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import phaseten.Card;
import phaseten.Color;
import phaseten.Rank;

public class Run implements PhaseType {

    private int runSize;

    public Run(int runSize) {
        this.runSize = runSize;
    }

    public int getRunSize() {
        return runSize;
    }

    @Override
    public boolean isValidType(ArrayList<Card> cards) {
        Collections.sort(cards, new RankComparator());
        if (cards.get(0).getColor() == Color.BLACK
            || cards.size() < runSize) return false;
        List<Rank> ranks = Arrays.asList(Rank.values());
	    int startingIndex = ranks.indexOf(cards.get(0).getRank());
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank() != ranks.get(i + startingIndex) ||
                cards.get(i).getColor() == Color.BLACK) {
                return false;
            }
        }
        return true;
    }
    
}
