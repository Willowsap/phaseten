public class IllegalPhaseException extends Exception {
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
