package phaseten;
import java.util.Stack;

import phaseten.exceptions.DiscardPileEmptyException;

import java.util.Collections;

public class Deck {

    public static final int DECK_SIZE = 108;
    public static final int NUM_DUP_NORMAL = 2;
    public static final int NUM_SKIP = 4;
    public static final int NUM_WILD = 8;

    Stack<Card> deck;
    Stack<Card> discard;

    public Deck() {
        buildDeck();
        shuffleDeck();
        this.discard = new Stack<Card>();
    }

    public Card drawFromDeck() {
        if (deck.empty()) returnDiscardToDeck();
        return deck.pop();
    }

    public Card drawFromDiscard() throws DiscardPileEmptyException {
        if (discard.empty())
            throw new DiscardPileEmptyException("Tried to draw from empty discard pile.");
        else
            return discard.pop();
    }

    public void addToDiscardPile(Card card) {
        this.discard.push(card);
    }

    public void discardFromDeck() {
        this.discard.push(this.deck.pop());
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void returnDiscardToDeck() {
        for (int i = 0; i < this.discard.size(); i++)
            deck.push(discard.pop());
        shuffleDeck();
    }

    private void buildDeck() {
        this.deck = new Stack<Card>();
        for (Color color : Color.values()) {
            if (color != Color.BLACK)
                for (Rank rank : Rank.values())
                    if (rank != Rank.SKIP && rank != Rank.WILD)
                        for (int i = 0; i < NUM_DUP_NORMAL; i++)
                            this.deck.push(new Card(rank, color));
            else {
                for (int i = 0; i < NUM_SKIP; i++) deck.push(new Card(Rank.SKIP, color));
                for (int i = 0; i < NUM_WILD; i++) deck.push(new Card(Rank.WILD, color));
            }
            
        }
    }
}
