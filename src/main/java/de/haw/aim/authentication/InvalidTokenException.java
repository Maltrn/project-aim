package de.haw.aim.authentication;

import javax.servlet.ServletException;

/**
 * Created by kk on 07.11.16.
 */
public class InvalidTokenException extends ServletException
{
    public InvalidTokenException()
    {
        super();
    }

    public InvalidTokenException(String message)
    {
        super(message);
    }
}
