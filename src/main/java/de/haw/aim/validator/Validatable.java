package de.haw.aim.validator;

public interface Validatable
{
    public void validate() throws ValueDoesntValidateToConfigFileException;
}
