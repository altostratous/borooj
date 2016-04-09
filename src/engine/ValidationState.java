package engine;

/**
 * Created by HP PC on 4/9/2016.
 */
public class ValidationState {
    private int code;
    private String message;

    public String toString() {
        return message;
    }

    public ValidationState(String message, int code) {
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationState)) return false;

        ValidationState that = (ValidationState) o;

        if (code != that.code) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    public static final ValidationState VALID = new ValidationState("The input is valid.", 0);
    public static final ValidationState INVALID_BASE = new ValidationState("The base position is invalid.", 1);
}
