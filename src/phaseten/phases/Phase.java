package phaseten.phases;

import java.util.ArrayList;

import phaseten.Card;
import phaseten.exceptions.IllegalPhaseException;

public abstract class Phase {
    public abstract boolean validPhase(ArrayList<Card> cards) throws IllegalPhaseException;
    public abstract String getPhaseRules();
}
