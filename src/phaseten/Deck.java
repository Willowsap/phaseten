package phaseten;
/**
 * Deck.java
 */

import java.util.Stack;
import java.util.Collections;

/**
 * Simulates a Phase Ten Deck and Discard Pile
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class Deck {

    /**
     * The number of cards in a Phase Ten deck.
     */
    public static final int DECK_SIZE = 108;

    /**
     * The number of duplicates of non-skip or wild cards.
     */
    public static final int NUM_DUP_NORMAL = 2;

    /**
     * The number of skip cards in the deck.
     */
    public static final int NUM_SKIP = 4;

    /**
     * The number of wild cards in the deck.
     */
    public static final int NUM_WILD = 8;

    /**
     * The deck is a stack of cards.
     */
    private Stack<Card> deck;

    /**
     * The discard pile is a stack of cards.
     */
    private Stack<Card> discard;

    /**
     * Constructs a new deck and initializes the discard stack.
     */
    public Deck() {
        buildDeck();
        shuffleDeck();
        this.discard = new Stack<Card>();
    }

    /**
     * Draws a card from the top of the deck and returns it.
     * The drawn card is removed from the deck.
     * If the deck is empty, the discard pile is shuffled and
     * returned to the deck.
     * 
     * @return - the top card on the deck.
     */
    public Card drawFromDeck() {
        if (deck.empty()) returnDiscardToDeck();
        return deck.pop();
    }

    /**
     * Draws the top card from the discard pile.
     * Throws an exception if the discard pile is empty.
     * The drawn card is returned and removed from the discard pile.
     * 
     * @return - the top card on the discard pile.
     *           null if the discard pile is empty.
     */
    public Card drawFromDiscard() {
        if (discard.empty()) return null;
        else return discard.pop();
    }

    /**
     * Adds a card to the top of the discard pile.
     * 
     * @param card - the card to add.
     */
    public void addToDiscardPile(Card card) {
        this.discard.push(card);
    }

    /**
     * Discards a card from the top of the deck into the discard pile.
     * The card is removed from the top of the deck
     * and added to the top of the discard pile.
     */
    public void discardFromDeck() {
        this.discard.push(this.deck.pop());
    }

    /**
     * Randomizes the order of cards in the deck stack.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Returns a copy of the card on top of the discard pile.
     * The card is not removed from the discard pile.
     * 
     * @return - a copy of the card on top of the discard pile.
     *           null if the discard pile is empty.
     */
    public Card seeTopOfDiscard() {
        return discard.empty() ? null : discard.peek().clone();
    }

    /**
     * Gets a copy of the discard pile.
     * 
     * @return - a copy of the discard pile.
     */
    public Stack<Card> getDiscardPile() {
        Stack<Card> t = new Stack<Card>();
        for (Card c : discard)
            t.push(c.clone());
        return t;
    }

    /**
     * Gets a copy of the deck stack.
     * 
     * @return - a copy of the deck stack.
     */
    public Stack<Card> getDeck() {
        Stack<Card> t = new Stack<Card>();
        for (Card c : deck)
            t.push(c.clone());
        return t;
    }

    /**
     * Takes all cards out of the discard pile and puts them in the deck.
     * Then shuffles the deck.
     */
    private void returnDiscardToDeck() {
        for (int i = 0; i < this.discard.size(); i++)
            deck.push(discard.pop());
        shuffleDeck();
    }

    /**
     * Creates all the cards needed for a Phase Ten deck
     * and adds them to the deck stack.
     */
    private void buildDeck() {
        this.deck = new Stack<Card>();
        for (Color color : Color.values()) {
            if (color != Color.BLACK)
                for (Rank rank : Rank.values())
                    if (rank != Rank.SKIP && rank != Rank.WILD)
                        for (int i = 0; i < NUM_DUP_NORMAL; i++)
                            this.deck.push(Card.getCard(rank, color));
            else {
                for (int i = 0; i < NUM_SKIP; i++) deck.push(Card.getCard(Rank.SKIP, color));
                for (int i = 0; i < NUM_WILD; i++) deck.push(Card.getCard(Rank.WILD, color));
            }
            
        }
    }
}
