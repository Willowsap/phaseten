package phaseten;
public class Card {
    private Rank rank;
    private Color color;

    public Card(Rank rank, Color color) {
        this.rank = rank;
        this.color = color;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean sameColor(Card card) {
        return this.color == card.getColor();
    }

    public boolean sameRank(Card card) {
        return this.rank == card.getRank();
    }
}