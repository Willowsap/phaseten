public class GameState {
    public static final int NUM_STARTING_CARDS = 10;

    private Deck deck;

    public void startNewGame(int numPlayers) {
        this.deck = new Deck();
        this.deck.discardFromDeck();
    }
}
