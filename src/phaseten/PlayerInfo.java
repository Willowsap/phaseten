package phaseten;
/**
 * PlayerInfo.java
 */

import java.util.ArrayList;
import java.util.Stack;

import phaseten.phases.Phase;

/**
  * This class provides information about the game to players.
  * It hides info that the player shouldn't have and only exposes
  * information that should be accessible.
  *
  * @author Willow Sapphire
  * @version 06/02/2022
  */
public class PlayerInfo {

    /**
     * The current round player from which to get info.
     */
    private RoundPlayer rp;

    /**
     * Creates a new PlayerInfo object.
     * 
     * @param rp - the round player.
     */
    public PlayerInfo(RoundPlayer rp) {
        this.rp = rp;
    }

    /**
     * Gets the current player's id.
     * 
     * @return - current player id.
     */
    public int getCurrPlayerId() {
        return rp.getCurrentPlayer();
    }

    /**
     * Gets the hand of the current player.
     * 
     * @return - the hand of the current player.
     */
    public ArrayList<Card> getCurrPlayerHand() {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (Card c : rp.getHands().get(rp.getCurrentPlayer()))
            hand.add(c.clone());
        return hand;
    }

    /**
     * Gets the phase of the current player.
     * 
     * @return - the phase of the current player.
     */
    public Phase getCurrPlayerPhase() {
        return rp.getPhases()[rp.getCurrentPlayer()];
    }

    /**
     * Get the number of cards in a particular player's hand.
     * 
     * @param playerId - the player to inspect.
     * 
     * @return - the number of cards in the given player's hand.
     */
    public int getNumCardsInPlayerHand(int playerId) {
        return rp.getHands().get(playerId).size();
    }

    /**
     * Gets the number of players in the game.
     * 
     * @return - the number of players in the game.
     */
    public int getNumPlayers() {
        return rp.getPlayers().length;
    }

    /**
     * Gets a copy of the discard pile.
     * 
     * @return - a copy of the discard pile.
     */
    public Stack<Card> getDiscardPile() {
        Stack<Card> t = new Stack<Card>();
        for (Card c : rp.getDeck().getDiscardPile())
            t.push(c.clone());
        return t;
    }

    /**
     * Gets the phase a particular player.
     * 
     * @param playerId - the player to inspect.
     * 
     * @return - the phase of the given player.
     */
    public Phase getPlayerPhase(int playerId) {
        return rp.getPhases()[playerId];
    }
}
