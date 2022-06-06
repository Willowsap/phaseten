package phaseten;
/**
 * WildCard.java
 */

/**
 * Simulates a Wild Card in Phase Ten.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class WildCard extends Card {

    /**
     * The rank that the wild card is being used as.
     */
    private Rank activeRank;
    
    /**
     * The color that the wild card is being used as.
     */
    private Color activeColor;

    /**
     * Constructs a wildcard.
     */
    public WildCard() {
        super(Rank.WILD, Color.BLACK);
        this.activeRank = null;
        this.activeColor = null;
    }

    /**
     * Select the rank and color that you want to use this wild card as.
     * 
     * @param rank - the rank you want to use.
     * @param color - the color you want to use.
     */
    public void setActiveAttributes(Rank rank, Color color) {
        if (rank == Rank.SKIP)
            throw new IllegalArgumentException(
                "Skip is not a valid active rank for wild cards.");
        this.activeRank = rank;
        this.activeColor = color;
    }

    @Override
    public Rank getActiveRank() {
        return this.activeRank;
    }

    @Override
    public Color getActiveColor() {
        return this.activeColor;
    }
    
    @Override
    public WildCard clone() {
        return new WildCard();
    }
}
