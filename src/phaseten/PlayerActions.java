package phaseten;
/**
 * PlayerActions.java
 */

import java.util.ArrayList;

import phaseten.exceptions.IllegalAddException;
import phaseten.exceptions.IllegalPhaseException;

 /**
  * Functions for players to use on their turn.
  */
public class PlayerActions {
    
    /**
     * The current round player from which to get info.
     */
    private RoundPlayer rp;

    /**
     * Constructs a new player actions object.
     * 
     * @param rp - the round player.r.
     */
    public PlayerActions(RoundPlayer rp) {
        this.rp = rp;
    }

    /**
     * Adds a card to a particular phase.
     * 
     * @param player - the player that has the phase being added to.
     * @param group - the card group being added to.
     * @param card - the card to add to the group.
     * 
     * @return - true if the card was successfully added. false if it was invalid.
     */
    public boolean addToPhase(int player, int group, Card card) {
        try {
            // check that the player has the card they are trying to add.
            if (rp.getHands().get(rp.getCurrentPlayer()).contains(card)) {
                this.rp.getPhases()[player].addToPhase(group, card);
                // remove the card from the player's hand after adding it.
                rp.getHands().get(rp.getCurrentPlayer()).remove(card);
                return true;
            }
            else return false;
        } catch (IllegalAddException e) {
            return false;
        }
    }

    /**
     * Phases the current player using the provided cards.
     * 
     * @param cards - the cards to phase with.
     * 
     * @return
     */
    public boolean phase(ArrayList<ArrayList<Card>> cards) {
        try {
            // check that the player has all the cards they are using.
            if (validatePhase(cards)) {
                this.rp.getPhases()[rp.getCurrentPlayer()].phase(cards);
                // remove the cards from the player's hand after phasing
                removeCardsFromCurrentPlayer(cards);
                return true;
            }
            else return false;
        } catch (IllegalPhaseException e) {
            return false;
        }
    }

    /**
     * Removes all the cards used to phase from the current player's hand.
     * It should already have been validated that the player has these cards.
     * Therefore this method does not check that the player has the cards
     * when it attempts to remove them.
     * 
     * @param cards - the cards used to phase.
     */
    private void removeCardsFromCurrentPlayer(ArrayList<ArrayList<Card>> cards) {
        for (ArrayList<Card> a : cards)
            for (Card c : a)
                rp.getHands().get(rp.getCurrentPlayer()).remove(c);
    }

    /**
     * Validates that the current player has all the cards they
     * are using to phase.
     * 
     * @param cards - the cards being used.
     * 
     * @return - true if the player has all the cards. false otherwise.
     */
    private boolean validatePhase(ArrayList<ArrayList<Card>> cards) {
        ArrayList<Card> t = new ArrayList<Card>();
        for (Card c : rp.getHands().get(rp.getCurrentPlayer()))
            t.add(c.clone());
        for (ArrayList<Card> a : cards) {
            for (Card c : a) {
                if (t.contains(c)) t.remove(c);
                else return false;
            }
        }
        return true;
    }
}
