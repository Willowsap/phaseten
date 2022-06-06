package phaseten;
/**
 * RoundPlayer.java
 */

import java.util.ArrayList;

import phaseten.phases.Phase;
import phaseten.players.Player;

/**
 * A class that can play rounds of Phase Ten.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class RoundPlayer {
    /**
     * The number of cards each player starts with in their hand.
     */
    public static final int NUM_STARTING_CARDS = 10;

    /**
     * The deck used in the round.
     */
    private Deck deck;

    /**
     * The hands for each player used in the round.
     */
    private ArrayList<ArrayList<Card>> hands;

    /**
     * The players in the round.
     */
    private Player[] players;
    
    /**
     * The phases for the round.
     */
    private Phase[] phases;

    /**
     * The scores for the game.
     */
    private int[] scores;

    /**
     * Tells whether each player has a skip card in front of them.
     */
    private boolean[] skips;

    /**
     * The current player at a given point in time.
     */
    private int cp;

    /**
     * Plays one round of Phase Ten.
     * 
     * @param players - the players.
     * @param phases - the phases that each player is on.
     * @param scores - the scores for each player.
     * @param firstPlayer - the player who goes first.
     */
    public void playRound(Player[] players, Phase[] phases, int[] scores, int firstPlayer) {
        this.players = players;
        this.phases = phases;
        this.scores = scores;
        this.cp = firstPlayer;
        setUp();
        while(!roundOver()) {
            if (!skip()) {
                draw();
                discard(play());
            }
            updateCp();
        }
        score();
    }

    /**
     * Accessor method for the players.
     * Does not prevent reference being changed.
     * 
     * @return - players.
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Accessor method for the phases.
     * Does not prevent reference being changed.
     * 
     * @return - phases.
     */
    public Phase[] getPhases() {
        return this.phases;
    }

    /**
     * Accessor method for the scores.
     * Does not prevent reference being changed.
     * 
     * @return - scores.
     */
    public int[] getScores() {
        return this.scores;
    }

    /**
     * Accessor method for the skips.
     * Does not prevent reference being changed.
     * 
     * @return - skips.
     */
    public boolean[] skips() {
        return this.skips;
    }

    /**
     * Accessor method for the deck.
     * Does not prevent reference being changed.
     * 
     * @return - deck.
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Accessor method for the deck.
     * Does not prevent reference being changed.
     * 
     * @return - deck.
     */
    public ArrayList<ArrayList<Card>> getHands() {
        return this.hands;
    }

    /**
     * Accessor method for the current player.
     * 
     * @return - cp.
     */
    public int getCurrentPlayer() {
        return this.cp;
    }
    /**
     * Scores a round.
     * Each player gets the sum of the value of cards in their hand.
     */
    private void score() {
        for (int i = 0; i < scores.length; i++)
            for (Card c : hands.get(i))
                scores[i] += c.getValue();
    }

    /**
     * Has the current player draw a card from either the deck or discard.
     */
    private void draw() {
        if (deck.seeTopOfDiscard() != null
            && deck.seeTopOfDiscard().getRank() != Rank.SKIP
            && players[cp].takeDiscard(new PlayerInfo(this)))
            hands.get(cp).add(deck.drawFromDiscard());
        else
            hands.get(cp).add(deck.drawFromDeck());
    }

    /**
     * Has the current player take their turn.
     * 
     * @return - the card that the current player chose to discard.
     */
    private Card play() {
        return players[cp].takeTurn(new PlayerInfo(this), new PlayerActions(this));
    }

    /**
     * Discards a specified card by the current player.
     * If the card is a skip, asks the player who they wish to skip.
     * Sets the skip array accordingly.
     * 
     * @param discard - the card being discarded.
     */
    private void discard(Card discard) {
        if (discard.getRank() == Rank.SKIP) {
            int p = players[cp].whoToSkip(new PlayerInfo(this));
            if (!skips[p]) {
                skips[p] = true;
            }
            else {
                System.out.printf("Round aborted. Player %d tried to "
                    + "skip player %d but player %d already had a skip "
                    + "in front of them\n", cp);
                System.exit(1);
            }
        } else {
            deck.addToDiscardPile(discard.clone());
            hands.get(cp).remove(discard);
        }
    }

    /**
     * Updates the current player using modular arithmatic.
     */
    private void updateCp() {
        cp = (cp + 1) % players.length;
    }

    /**
     * Skips a player.
     * 
     * @return true if the player has been skipped. false otherwise.
     */
    private boolean skip() {
        if (!skips[cp]) return false;
        deck.addToDiscardPile(Card.getCard(Rank.SKIP, Color.BLACK));
        return true;
    }

    /**
     * Sets up a new round.
     */
    private void setUp() {
        this.deck = new Deck();
        deck.discardFromDeck();
        if (deck.seeTopOfDiscard().getRank() == Rank.SKIP)
            skips[cp] = true;
        this.hands = new ArrayList<ArrayList<Card>>(players.length);
        this.skips = new boolean[players.length];
        dealHands();
    }

    /**
     * Checks if a round has ended based on if a player has no more cards.
     * 
     * @return - true if the round has ended. false otherwise.
     */
    private boolean roundOver() {
        for (ArrayList<Card> hand : hands) 
            if (hand.size() == 0)
                return true;
        return false;
    }

    /**
     * Deals cards from the deck to each hand.
     * 
     * @param deck - the deck from which to deal.
     * @param hands - the hands to add cards to.
     */
    private void dealHands() {
        for (int i = 0; i < hands.size(); i++) {
            hands.add(new ArrayList<Card>(NUM_STARTING_CARDS * 2));
            for (int j = 0; j < NUM_STARTING_CARDS; j++)
                hands.get(i).add(deck.drawFromDeck());
        }
    }
}
