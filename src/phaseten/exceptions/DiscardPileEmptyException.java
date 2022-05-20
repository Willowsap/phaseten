package phaseten.exceptions;

public class DiscardPileEmptyException extends PhaseTenException {
    private static final long serialVersionUID = 1L;

    public DiscardPileEmptyException(String message) {
        super(message);
    }
    
    public DiscardPileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DiscardPileEmptyException(Throwable cause) {
        super(cause);
    }
}
