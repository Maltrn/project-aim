package de.haw.aim.rest;

import de.haw.aim.validator.ValueDoesntValidateToConfigFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler
{

    @ExceptionHandler(value = { ValueDoesntValidateToConfigFileException.class })
    public ResponseEntity<String> invalidValue(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
