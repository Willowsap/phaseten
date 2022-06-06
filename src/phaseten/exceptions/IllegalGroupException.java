package phaseten.exceptions;
/**
 * IllegalGroupException.java
 */

 /**
  * An exception thrown when a player tried to lay down a phase
  * but the phase is not valid.
  *
  * @author Willow Sapphire
  * @version 05/31/2022
  */
public class IllegalGroupException extends PhaseTenException {
    private static final long serialVersionUID = 1L;

    public IllegalGroupException(String message) {
        super(message);
    }
    
    public IllegalGroupException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalGroupException(Throwable cause) {
        super(cause);
    }
}
