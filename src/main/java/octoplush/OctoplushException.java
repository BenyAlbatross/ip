package octoplush;

/**
 * Exception thrown when errors occur in the Octoplush application.
 */
public class OctoplushException extends RuntimeException {
    /**
     * Creates a new OctoplushException with the specified error message.
     *
     * @param message The error message.
     */
    public OctoplushException(String message) {
        super(message);
    }
}