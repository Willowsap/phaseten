public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public boolean sameSuit(Card card) {
        return this.suit == card.getSuit();
    }

    public boolean sameRank(Card card) {
        return this.rank == card.getRank();
    }
}