package phaseten.exceptions;

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
