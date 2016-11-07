package de.haw.aim.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


public class AuthenticationFilter implements Filter
{

    @Autowired
    AuthenticationCompoment authenticationCompoment;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException
    {
        //System.out.println("inside filter");

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if (isPublicApiCall(httpRequest))
        {
            // System.out.println("public API call");
            // Do nothing
            chain.doFilter(req, res);
        } else
        {
            // System.out.println("secured API call");
            // API call must be secured via token authentication
            String authorizationHeader = httpRequest.getHeader("Authorization");
            // Check if the HTTP Authorization header is present and formatted correctly
            if (authorizationHeader == null || !authorizationHeader.startsWith("TOKEN "))
            {
                throw new MandatoryAuthorizationHeaderFieldIsMissingException();
            }

            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("TOKEN".length()).trim();

            authenticationCompoment.verifyToken(token);

            HttpServletResponse response = (HttpServletResponse) res;
            System.out.println("läuft");
            chain.doFilter(req, res);
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