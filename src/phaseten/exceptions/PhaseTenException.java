package phaseten.exceptions;
/**
 * PhaseTenException.java
 */

/**
 * An exception class that all phase ten exceptions will subclass.
 * 
 * @author Willow Sapphire
 * @version 05/31/2022
 */
public class PhaseTenException extends Exception {
    private static final long serialVersionUID = 1L;

    public PhaseTenException(String message) {
        super(message);
    }
    
    public PhaseTenException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PhaseTenException(Throwable cause) {
        super(cause);
    }
}
