import java.util.ArrayList;

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
        return false;
    }
    
}
