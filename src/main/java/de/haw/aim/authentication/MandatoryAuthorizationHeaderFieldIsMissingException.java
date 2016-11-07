package de.haw.aim.authentication;

import javax.servlet.ServletException;

/**
 * Created by kk on 07.11.16.
 */
public class MandatoryAuthorizationHeaderFieldIsMissingException extends ServletException
{
    public MandatoryAuthorizationHeaderFieldIsMissingException()
    {
        super();
    }

    public MandatoryAuthorizationHeaderFieldIsMissingException(String message)
    {
        super(message);
    }
}
