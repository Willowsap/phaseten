package phaseten.phases;
/**
 * Phase.java
 */

import java.util.ArrayList;

import phaseten.Card;
import phaseten.exceptions.IllegalAddException;
import phaseten.exceptions.IllegalGroupException;
import phaseten.exceptions.IllegalPhaseException;

/**
 * Simulates a phase in Phase Ten
 * Players receive a phase object matching their current phase each round.
 * Players can lay down their phase and, once laid down, add to it.
 * The phases are made up of a collection of CardGroups,
 * each of which follows a pattern.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class Phase {

    /**
     * The id of this phase (1 - 10).
     */
    private int phaseId;

    /**
     * All CardGroups in this phase
     */
    private CardGroup[] cardGroups;

    /**
     * Constructs a phase.
     * Private since phases are only created by the getPhase static method.
     * Clients are not able to create their own phases.
     * 
     * @param phaseId - the id of the phase (1 - 10).
     * @param cardGroups - the collection of card groups in the phase.
     */
    private Phase(int phaseId, CardGroup[] cardGroups) {
        this.phaseId = phaseId;
        this.cardGroups = cardGroups;
    }

    /**
     * Checks if a given set of cards satisfies this phase.
     * The index of each ArrayList of cards must match the
     * index of the card groups.
     * ie: if the groups are: set of 4, run of 4.
     * The first ArrayList must be the set and the second the run.
     * 
     * @param cards - the solutions to each phase group.\
     * 
     * @return - true if the cards satisfy the phase. false otherwise.
     */
    public boolean validPhase(ArrayList<ArrayList<Card>> cards) {
        if (cards.size() == cardGroups.length) {
            for (int i = 0; i < cardGroups.length; i++)
                if (!cardGroups[i].isValidComplete(cards.get(i))) return false;
            return true;
        }
        return false;
    }

    /**
     * Adds cards to an existing phase.
     * The phase msut have phased already to be added onto.
     * 
     * @param cardGroupIndex - which card group in the phase to add to.
     * @param card - the card to add
     * 
     * @throws IllegalAddException - thrown if the card cannot be added to the group
     *                               or if the phase has not phased yet
     */
    public void addToPhase(int cardGroupIndex, Card card) throws IllegalAddException {
        if (!hasPhased())
            throw new IllegalAddException("Tried to add to a phase that has not phased yet");
        if (cardGroupIndex < 0 || cardGroupIndex >= cardGroups.length)
            throw new IllegalAddException("Invalid phase type index selected: " + cardGroupIndex);
        cardGroups[cardGroupIndex].addToGroup(card);
    }

    /**
     * Phasing refers to laying down cards that are valid for the phase.
     * This method phases this phase.
     * 
     * @param cards - the cards used to phase.
     */
    public void phase(ArrayList<ArrayList<Card>> cards) throws IllegalPhaseException {
        if (validPhase(cards)) {
            for (int i = 0; i < cardGroups.length; i++) {
                try {
                    cardGroups[i].complete(cards.get(i));
                } catch (IllegalGroupException e) {
                    System.out.println("Illegal Group Exception occurred during a phase\n"
                        + "The groups were checked to be valid before they were completed\n"
                        + "There is a bug somewhere. Check the validPhase method.");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        } else {
            throw new IllegalPhaseException("Invalid phase attempted on phase " + phaseId);
        }
    }

    /**
     * Check if this phase has phased (been laid down) yet.
     * 
     * @return true if the phase has phased. false otherwise.
     */
    public boolean hasPhased() {
        // this checks that every card group has been laid down
        // however, it should be impossible for some to be laid
        // down and not others
        for (CardGroup p : this.cardGroups)
            if (!p.hasBeenCompleted()) return false;
        return true;
    }

    /**
     * Getter for the card groups in the phase.
     * Returns a copy so they cannot be manipulated.
     * Changes to the real card groups must be done
     * through methods in the Phase class
     * 
     * @return a copy of cardGroups
     */
    public CardGroup[] getCardGroups() {
        CardGroup[] t = new CardGroup[cardGroups.length];
        for (int i = 0; i < cardGroups.length; i++)
            t[i] = cardGroups[i].clone();
        return t;
    }

    /**
     * Getter for the number of card groups in the phase.
     * 
     * @return the number of card groups in the phase.
     */
    public int getNumCardGroups() {
        return this.cardGroups.length;
    }

    /**
     * Getter method for the phase id.
     * 
     * @return - this phases's id (1 - 10).
     */
    public int getPhaseId() {
        return this.phaseId;
    }

    /**
     * Creates an exact copy of the phase.
     * 
     * @return - a new copy of this phase.
     */
    @Override
    public Phase clone() {
        CardGroup[] t = new CardGroup[this.cardGroups.length];
        for (int i = 0; i < this.cardGroups.length; i++)
            t[i] = cardGroups[i].clone();
        return new Phase(this.phaseId, t);
    }

    /**
     * This function defines and creates the phases that exist in Phase Ten.
     * The game will call this function to get phases at a particular level.
     * Phases cannot be created any way except through this function.
     * 
     * 1: two sets of 3
     * 2: set of 3, run of 4
     * 3: set of 4, run of 4
     * 4: run of 7
     * 5: run of 8
     * 6: run of 9
     * 7: two sets of 4 
     * 8: flush of 7
     * 9: set of 5, set of 2
     * 10:set of 5, set of 3
     * 
     * @param phaseNum - the number of phase to create.
     * 
     * @return - the appropriate phase object.
     *           null is returned if an invalid number is given.
     */
    public static Phase getPhase(int phaseNum) {
        switch(phaseNum) {
            case 1:
                return new Phase(1, new CardGroup[]{new Set(3),new Set(3)});
            case 2:
                return new Phase(2, new CardGroup[]{new Set(3), new Run(4)});
            case 3:
                return new Phase(3, new CardGroup[]{new Set(4), new Run(4)});
            case 4:
                return new Phase(4, new CardGroup[]{new Run(7)});
            case 5:
                return new Phase(5, new CardGroup[]{new Run(8)});
            case 6:
                return new Phase(6, new CardGroup[]{new Run(9)});
            case 7:
                return new Phase(7, new CardGroup[]{new Set(4), new Set(4)});
            case 8:
                return new Phase(8, new CardGroup[]{new Flush(7)});
            case 9:
                return new Phase(9, new CardGroup[]{new Set(5), new Set(2)});
            case 10:
                return new Phase(10, new CardGroup[]{new Set(5), new Set(3)});
            case 11:
                return new Phase(11, null);
            default:
                return null;
        }
    }
}
