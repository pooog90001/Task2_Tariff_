package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/28/2017.
 */
public class TariffParseException extends Exception {

    public TariffParseException() {
        super();
    }

    public TariffParseException(String message) {
        super(message);
    }

    public TariffParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TariffParseException(Throwable cause) {
        super(cause);
    }
}
