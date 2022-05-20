import java.util.ArrayList;

public class Set implements PhaseType {

    private int setSize;

    public Set(int setSize) {
        this.setSize = setSize;
    }

    public int getSetSize() {
        return this.setSize;
    }

    @Override
    public boolean isValidType(ArrayList<Card> cards) {
        Rank r = cards.get(0).getRank();
        int validCards = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank() == r) {
                validCards++;
            } else {
                return false;
            }
        }
        return validCards >= setSize;
    }
    
}
