package phaseten.players;
/**
 * Player.java
 */

import phaseten.Card;
import phaseten.PlayerActions;
import phaseten.PlayerInfo;

/**
 * This is an interface that all Phase Ten Players must implement.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public interface Player {
    /**
     * Decide whether you want to draw from the discard pile or deck.
     * This function is always called by the game immediately before
     * calling the takeTurn function.
     * 
     * @param playerInfo - All information that the player has.
     * 
     * @return - true if you want the card on the discard pile.
     *           false to draw from the deck.
     */
    public boolean takeDiscard(PlayerInfo playerInfo);

    /**
     * Take whatever actions you want during your turn.
     *
     * @param playerInfo - All information that the player has.
     *  
     * @return the card you want to discard.
     */
    public Card takeTurn(PlayerInfo playerInfo, PlayerActions playerActions);

    /**
     * This function is called if you discard a skip card during takeTurn.
     * The player must decide which player they want to skip.
     * 
     * @param playerInfo - All information that the player has.
     * 
     * @return the index of the player you want to skip.
     *         You cannot choose a player with a skip card already on them.
     */
    public int whoToSkip(PlayerInfo playerInfo);
}
