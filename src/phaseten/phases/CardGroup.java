package phaseten.phases;
/**
 * CardGroup.java
 */

import java.util.ArrayList;

import phaseten.Card;
import phaseten.exceptions.IllegalAddException;
import phaseten.exceptions.IllegalGroupException;

/**
 * Interface that all card groups must implement.
 * Card groups require a certain pattern of cards.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public abstract class CardGroup implements Cloneable {

    /**
     * The cards in the completed group
     * Is empty until completed.
     */
    protected ArrayList<Card> cardsInGroup;

    /**
     * The number of cards needed to complete the group.
     * Not necessarily the same as the size of cardsInGroup
     * since cards can be added after the group is complete.
     */
    protected int groupSize;

    /**
     * Constructor for CardGroup.
     * Sets groupSize and initializes cardsInGroup to an empty list.
     * 
     * @param groupSize - the number of cards needed for the group.
     */
    public CardGroup(int groupSize) {
        this.groupSize = groupSize;
        this.cardsInGroup = new ArrayList<Card>(groupSize * 2);
    }

    /**
     * Getter for the cards in the group.
     * Returns a deep copy of the list so it cannot be tampered with.
     * 
     * @return a deep copy of cardsInGroup.
     */
    public ArrayList<Card> getCardsInGroup() {
        ArrayList<Card> t = new ArrayList<Card>();
        for (Card c : cardsInGroup) t.add(c.clone());
        return t;
    }

    /**
     * Checks if the card group has been completed.
     * This can be checked by seeing if any cards are in the list.
     * 
     * @return true if the card group has been complete. false otherwise.
     */
    public boolean hasBeenCompleted() {
        return cardsInGroup.size() > 0;
    }

    /**
     * All card groups have a minimum number of cards need to complete them.
     * This function returns that number.
     * 
     * @return
     */
    public int getNumCardsRequired() {
        return this.groupSize;
    }

    /**
     * Getter for the number of cards currently in the group.
     * Will be zero before the group is completed.
     * 
     * @return the number of cards currently in the group.
     */
    public int getNumCardsInGroup() {
        return this.cardsInGroup.size();
    }

    /**
     * Complete the group by providing a list of cards that meet the rule.
     * 
     * @param cards - the cards to complete the group
     * @throws IllegalGroupException - thrown if the cards do not follow the rule.
     */
    public final void complete(ArrayList<Card> cards) throws IllegalGroupException {
        if (!isValidComplete(cards)) {
            throw new IllegalGroupException(String.format(
                "%s does not satisfy %s", cards, this.getDescription()));
        }
        else {
            for (Card c : cards) cardsInGroup.add(c.clone());
            postComplete();
        }
    }

    /**
     * Adds a card to the card group.
     * This can only be done if the group has been completed.
     * 
     * @param card - the card to add.
     * @throws IllegalAddException - thrown if the card to add is invalid.
     */
    public void addToGroup(Card card) throws IllegalAddException {
        if (!hasBeenCompleted()) {
            throw new IllegalAddException(
                "Group must be completed before cards may be added to it.");
        }
        else if (!isValidAdd(card)) {
            throw new IllegalAddException(String.format(
                "card: '%s' cannot be added to group: %s", card, cardsInGroup));
        }
        else {
            cardsInGroup.add(card.clone());
            postAdd();
        }
    }

    /**
     * Adds common elements to a clone.
     * Used by sublclasses so they do not all need to implement
     * the same copying routine.
     * 
     * Subclasses should call this method in their clone implementation.
     * 
     * @param t - the CardGroup clone that was created.
     */
    protected final void baseClone(CardGroup t) {
        t.cardsInGroup = new ArrayList<Card>(this.cardsInGroup.size());
        for (Card c : this.cardsInGroup)
            t.cardsInGroup.add(c.clone());
    }

    /**
     * This function is run after successfulluy completing the group.
     * Subclasses can use it to run some logic after completing.
     */
    protected void postComplete() {}

    /**
     * This function is run after successfulluy adding to the group.
     * Subclasses can use it to run some logic after adding.
     */
    protected void postAdd() {}

    /**
     * Checks if a given list of cards satisfies the group rule.
     * @param cards - the cards to check.
     * 
     * @return - true if the rule is satisfied. false otherwise.
     */
    public abstract boolean isValidComplete(ArrayList<Card> cards);

    /**
     * Checks if a give card is valid to be added to the group.
     * 
     * @param card - the card to add.
     * 
     * @return - true if the card is valid to add. false otherwise.
     */
    public abstract boolean isValidAdd(Card card);

    /**
     * Create a copy of the card group.
     * 
     * @return - an exact replica of the card group.
     */
    public abstract CardGroup clone();

    /**
     * A text description of the group for humans to read.
     * ex: "Run of 5"
     * 
     * @return - the description as a String.
     */
    public abstract String getDescription();

    /**
     * A printable version of the group.
     * This should include information about the rule for the group
     * and any cards currently added to it if it is completed.
     * 
     * The difference between it and getDescription is that getDescription
     * is a smaller, simpler description.
     * @return
     */
    public abstract String toString();
}
