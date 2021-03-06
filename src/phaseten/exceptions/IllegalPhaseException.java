package phaseten.exceptions;
/**
 * IllegalPhaseException.java
 */

 /**
  * An exception thrown when a player tried to lay down a phase
  * but the phase is not valid.
  *
  * @author Willow Sapphire
  * @version 05/31/2022
  */
public class IllegalPhaseException extends PhaseTenException {
    private static final long serialVersionUID = 1L;

    public IllegalPhaseException(String message) {
        super(message);
    }
    
    public IllegalPhaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalPhaseException(Throwable cause) {
        super(cause);
    }
}
