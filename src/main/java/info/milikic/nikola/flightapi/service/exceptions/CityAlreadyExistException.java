package info.milikic.nikola.flightapi.service.exceptions;

public class CityAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5718827134377208347L;

    public CityAlreadyExistException() {
        super();
    }

    public CityAlreadyExistException(final String message) {
        super(message);
    }
}
