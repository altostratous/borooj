package engine;

/**
 * Created by HP PC on 4/9/2016.
 * Class to model validation states for the ui.
 */
public class ValidationState {
    // code of the validation error
    private int code;
    // the error message
    private String message;

    /**
     * returns the message
     *
     * @return a string
     */
    public String toString() {
        return message;
    }

    /**
     * Constructs the validation error based of message and code
     * @param message message
     * @param code code
     */
    public ValidationState(String message, int code) {
        this.code = code;
        this.message = message;
    }

    /**
     * The equivalence logic is based on the code of the validation state
     * @param o object
     * @return return if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationState)) return false;

        ValidationState that = (ValidationState) o;

        if (code != that.code) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    // Some specific validation states
    /**
     * Valid state
     */
    public static final ValidationState VALID = new ValidationState("The input is valid.", 0);
    /**
     * Error of unavailable base to base an object
     */
    public static final ValidationState INVALID_BASE = new ValidationState("The base position is invalid.", 1);
    /**
     * Error of file for example config file
     */
    public static final ValidationState INVALID_FILE = new ValidationState("The file address or the file content has some problem.", 2);
}
