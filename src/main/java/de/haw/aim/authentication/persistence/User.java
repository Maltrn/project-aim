package de.haw.aim.authentication.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rene on 31.10.2016.
 */
@Document
public class User
{
    @Id
    protected String id;
    protected String username;
    protected String pw;
    protected String currentToken;

    public User()
    {

    }

    public User(String username, String pw)
    {
        this.username = username;
        this.pw = pw;
    }


    public String getUsername()
    {
        return username;
    }

    public String getCurrentToken()
    {
        return currentToken;
    }

    public void setCurrentToken(String currentToken)
    {
        this.currentToken = currentToken;
    }


    public boolean checkPW(String pw)
    {
        return pw.equals(this.pw);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
