package phaseten;
/**
 * Game.java
 */

import phaseten.phases.Phase;
import phaseten.players.Player;

/**
 * Simulates games of the card game Phase Ten.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class Game {

    /**
     * The players in the game.
     */
    private Player[] players;

    /**
     * The scores for each player.
     * Indices match.
     */
    private int[] scores;

    /**
     * The current phase each player is on.
     * Indices match.
     */
    private Phase[] phases;

    /**
     * The number of rounds played.
     */
    private int roundNumber;

    /**
     * The index of the winning player.
     */
    private int winner;

    /**
     * Constructs a new game.
     * 
     * @param players - the players in the game.
     */
    public Game(Player[] players) {
        setPlayers(players);
    }

    /**
     * Plays a game of Phase Ten.
     */
    public void playGame() {
        setUpGame();
        RoundPlayer rp = new RoundPlayer();
        while (!gameOver()) {
            rp.playRound(players, phases, scores, roundNumber++ % players.length);
            incrementPhases();
        }
    }

    /**
     * Accessor method for the players.
     * 
     * @return - players.
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Mutator method for the players in the game.
     * 
     * @param players - new players.
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Accessor method for the scores from the last game played.
     * 
     * @return - scores.
     */
    public int[] getScoresFromLastGame() {
        return this.scores;
    }

    /**
     * Accessor method for the winner of the last game played.
     * 
     * @return - winnter.
     */
    public int getWinningPlayer() {
        return this.winner;
    }
    
    /**
     * Accessor method for the number of rounds the last game took.
     * 
     * @return - roundNumber.
     */
    public int getNumberOfRoundsPlayed() {
        return this.roundNumber;
    }

    /**
     * Prints a report on the last game played.
     */
    public void reportGameResults() {
        System.out.printf("Game over after %d rounds.\n", this.roundNumber);
        System.out.printf("Player %d won!\n", winner);
        System.out.printf("Final Scores:\n");
        for (int i = 0; i < this.scores.length; i++)
            System.out.printf("Player %d: %d points\n");
    }

    /**
     * Increases players phases between rounds if they phased.
     */
    private void incrementPhases() {
        for (Phase p : phases)
            if (p.hasPhased()) p = Phase.getPhase(p.getPhaseId() + 1);
            else p = Phase.getPhase(p.getPhaseId());
    }

    /**
     * Checks if a game is over by seeing if anyone completed phase ten.
     * 
     * @return - true if the game has ended. false otherwise.
     */
    private boolean gameOver() {
        for (Phase p : phases)
            if (p.getPhaseId() == 11)
                return true;
        return false;
    }

    /**
     * Sets up a new game.
     * Initializes scores to a new array of zeros.
     * Initializes the phases to phase 1 for each player.
     */
    private void setUpGame() {
        initializePhases();
        this.roundNumber = 0;
        this.scores = new int[this.players.length];
    }

    /**
     * Initializes the phases array to a new array of phases
     * the same length as players, with all entries at phase one.
     */
    private void initializePhases() {
        phases = new Phase[players.length];
        for (int i = 0; i < phases.length; i++)
            phases[i] = Phase.getPhase(1);
    }
}
