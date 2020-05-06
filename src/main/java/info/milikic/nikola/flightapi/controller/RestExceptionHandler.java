package info.milikic.nikola.flightapi.controller;

import info.milikic.nikola.flightapi.controller.dto.ErrorResponse;
import info.milikic.nikola.flightapi.security.jwt.InvalidJwtAuthenticationException;
import info.milikic.nikola.flightapi.service.exceptions.CityAlreadyExistException;
import info.milikic.nikola.flightapi.service.exceptions.UserAlreadyExistException;
import info.milikic.nikola.flightapi.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse userAlreadyExists(UserAlreadyExistException e) {
        log.debug("User already exists exception received with message {}", e.getMessage());

        return new ErrorResponse(ErrorCode.USER_ALREADY_EXISTS, e.toString(), e.getMessage());
    }

    @ExceptionHandler(value = {CityAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cityAlreadyExists(CityAlreadyExistException e) {
        log.debug("City already exists exception received with message {}", e.getMessage());

        return new ErrorResponse(ErrorCode.CITY_ALREADY_EXISTS, e.toString(), e.getMessage());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse badRequest(RuntimeException e) {
        return new ErrorResponse(ErrorCode.BAD_REQUEST, e.toString(), e.getMessage());
    }

    @ExceptionHandler(value = { InvalidJwtAuthenticationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse unauthorized(RuntimeException e) {
        return new ErrorResponse(ErrorCode.UNAUTHORIZED, e.toString(), e.getMessage());
    }
}
