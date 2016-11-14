package de.haw.aim.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFilter implements Filter
{

    @Autowired
    AuthenticationCompoment authenticationCompoment;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        if (isPublicApiCall(httpRequest))
        { // public API call therefore continue without checking authentication
            chain.doFilter(req, res);
        } else
        { // API call that should be secured by a valid Token given in HTTP header
            String authorizationHeader = httpRequest.getHeader("Authorization");
            // Check if the HTTP Authorization header is present and formatted correctly
            if (authorizationHeader == null || !authorizationHeader.startsWith("TOKEN "))
            {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Incorrect Authorization header field.  It should look like this: 'Authorization: TOKEN <insert current token here>'");
            } else
            { // Token was given check if it's valid
                // Extract the token from the HTTP Authorization header
                String token = authorizationHeader.substring("TOKEN".length()).trim();

                if (!(authenticationCompoment.verifyToken(token) || token.equals("handsomeTOKEN"))) // handsomeTOKEN just for debugging perposes
                {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "Token '" + token + "' is invalid. You should log-in again to get a new valid token.");
                } else

                { // Token is valid so the controller can do the rest
                    chain.doFilter(req, res);
                }
            }
        }

    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    private boolean isPublicApiCall(HttpServletRequest req)
    {
        String method = req.getMethod();
        String uri = req.getRequestURI().toString();
        //System.out.println(method);
        //System.out.println(uri);
        return (method.equals("POST") && uri.matches("^.*/login$")) ||
                (method.equals("GET") &&
                        (uri.matches("^.*/file/[a-z0-9]*$") ||
                                uri.matches("^.*/vendor$") ||
                                uri.matches("^.*/vendor/[a-z0-9]*$") ||
                                uri.matches("^.*/product$") ||
                                uri.matches("^.*/product/[a-z0-9]*$")) ||
                                uri.matches("^.*/(swagger.*|webjars/.*|v2.*|validatorUrl.*)$"));
    }

}