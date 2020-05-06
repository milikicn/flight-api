package info.milikic.nikola.flightapi.service.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 6686388868328923124L;

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
