package info.milikic.nikola.flightapi.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = -366300888633355846L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
