package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/26/2017.
 */
public class ValueLessZeroException extends Exception {

    public ValueLessZeroException() {
        super();
    }

    public ValueLessZeroException(String message) {
        super(message);
    }

    public ValueLessZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueLessZeroException(Throwable cause) {
        super(cause);
    }
}
