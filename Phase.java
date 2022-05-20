import java.util.ArrayList;

public abstract class Phase {
    public abstract boolean validPhase(ArrayList<Card> cards) throws IllegalPhaseException;
    public abstract String getPhaseRules();
}
