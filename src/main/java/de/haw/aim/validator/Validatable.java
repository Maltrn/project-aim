package de.haw.aim.validator;

public interface Validatable
{
    void validate() throws ValueDoesntValidateToConfigFileException;
}