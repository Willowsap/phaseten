package phaseten.phases;

import java.util.ArrayList;

import phaseten.Card;
import phaseten.exceptions.IllegalPhaseException;

public class PhaseOne extends Phase {

    @Override
    public boolean validPhase(ArrayList<Card> cards) throws IllegalPhaseException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getPhaseRules() {
        return "Set3,Set3";
    }
    
}
