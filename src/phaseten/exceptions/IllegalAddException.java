package phaseten.exceptions;
/**
 * IllegalAddException.java
 */

 /**
  * An exception thrown when a card is added to a phase
  * and is not a valid addition to the phase.
  *
  * @author Willow Sapphire
  * @version 05/31/2022
  */
public class IllegalAddException extends PhaseTenException {
    private static final long serialVersionUID = 1L;

    public IllegalAddException(String message) {
        super(message);
    }
    
    public IllegalAddException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalAddException(Throwable cause) {
        super(cause);
    }
}
