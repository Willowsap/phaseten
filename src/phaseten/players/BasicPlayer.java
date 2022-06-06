package phaseten.players;

import java.util.ArrayList;

import phaseten.Card;
import phaseten.PlayerActions;
import phaseten.PlayerInfo;
import phaseten.phases.Phase;

public class BasicPlayer implements Player {

    @Override
    public boolean takeDiscard(PlayerInfo playerInfo) {
        return false;
    }

    @Override
    public Card takeTurn(PlayerInfo playerInfo, PlayerActions playerActions) {
        ArrayList<Card> hand = playerInfo.getCurrPlayerHand();
        Phase phase = playerInfo.getCurrPlayerPhase();
    }

    @Override
    public int whoToSkip(PlayerInfo playerInfo) {
        return playerWithFewestCards(playerInfo);
    }

    /**
     * Cets the player with the fewest cards in their hand.
     * Excludes the current player.
     * 
     * @param playerInfo - info about the game.
     * 
     * @return - the id of the player with the fewest cards.
     */
    private int playerWithFewestCards(PlayerInfo playerInfo) {
        int minPlayer = -1;
        int minCards = Integer.MAX_VALUE;
        for (int i = 0; i < playerInfo.getNumPlayers(); i++) {
            if (i == playerInfo.currPlayerId()) continue;
            if (playerInfo.getNumCardsInPlayerHand(i) < minCards) {
                minCards = playerInfo.getNumCardsInPlayerHand(i);
                minPlayer = i;
            }
        }
        return minPlayer;
    }
}
