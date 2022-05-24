package phaseten.phases;

import java.util.ArrayList;

import phaseten.Card;

public interface PhaseType {
    public boolean isValidType(ArrayList<Card> cards);
}
