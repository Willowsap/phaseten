package phaseten;
/**
 * Card.java
 */

/**
  * Simulates a Phase Ten card.
  * 
  * @author Willow Sapphire
  * @version 05/31/2022
  */
public class Card {

    /**
     * The rank of the card.
     */
    protected Rank rank;

    /**
     * The color of the card.
     */
    protected Color color;

    /**
     * The points the card is worth if left in a player's hand.
     */
    protected int value;

    /**
     * Creates a phase ten card.
     * Cannot be called by clients.
     * Protected to prevent illegal cards from being created.
     * 
     * @param rank - the rank of the card.
     * @param color - the color of the card.
     */
    protected Card(Rank rank, Color color) {
        this.rank = rank;
        this.color = color;
        this.value = GET_VALUE(rank);
    }

    /**
     * Getter for the card's value.
     * 
     * @return - value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter for the card's rank.
     * 
     * @return - rank.
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Getter for the card's color.
     * 
     * @return - color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter for the card's active rank.
     * For normal cards this is the same as their rank.
     * 
     * @return - rank.
     */
    public Rank getActiveRank() {
        return this.rank;
    }

    /**
     * Getter for the card's active color.
     * For normal cards this is the same as their color.
     * 
     * @return - color.
     */
    public Color getActiveColor() {
        return this.color;
    } 

    /**
     * Two cards are considered equal if they have the same rank and color.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Card && ((Card)o).getRank() == rank
        && ((Card)o).getColor() == color;
    }

    @Override
    public Card clone() {
        return new Card(this.rank, this.color);
    }

    @Override
    public String toString() {
        return this.color + " " + this.rank;
    }

    /**
     * Gets a new card of a particular rank and color.
     * Skips and Wilds are forced to be black.
     * Wilds produce a WildCard class.
     * All cards are created through this factory method.
     * 
     * @param rank - rank for the card.
     * @param color - color for the card.
     * 
     * @return the requested card.
     */
    public static Card getCard(Rank rank, Color color) {
        if (rank == Rank.WILD) return new WildCard();
        else if (rank == Rank.SKIP) return new Card(Rank.SKIP, Color.BLACK);
        else return new Card(rank, color);
    }

    public static final int GET_VALUE(Rank rank) {
        switch(rank) {
            case WILD:
                return 25;
            case SKIP:
                return 15;
            case TWELVE:
            case ELEVEN:
            case TEN:
                return 10;
            default:
                return 5;
        }
    }
}
