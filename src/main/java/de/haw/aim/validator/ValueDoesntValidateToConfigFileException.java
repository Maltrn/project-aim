package de.haw.aim.validator;

public class ValueDoesntValidateToConfigFileException extends Exception
{
    public ValueDoesntValidateToConfigFileException(String message)
    {
        super(message);
    }
}