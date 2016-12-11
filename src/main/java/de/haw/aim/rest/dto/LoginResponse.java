package de.haw.aim.rest.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


/**
 * LoginResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-10-31T08:42:18.273Z")

public class LoginResponse
{
    private String currentToken = null;

    private String username = null;

    public LoginResponse currentToken(String currentToken)
    {
        this.currentToken = currentToken;
        return this;
    }

    /**
     * Get currentToken
     *
     * @return currentToken
     **/
    @ApiModelProperty(value = "")
    public String getCurrentToken()
    {
        return currentToken;
    }

    public void setCurrentToken(String currentToken)
    {
        this.currentToken = currentToken;
    }

    public LoginResponse username(String username)
    {
        this.username = username;
        return this;
    }

    /**
     * Get username
     *
     * @return username
     **/
    @ApiModelProperty(value = "")
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        LoginResponse loginResponse = (LoginResponse) o;
        return Objects.equals(this.currentToken, loginResponse.currentToken) &&
                Objects.equals(this.username, loginResponse.username);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(currentToken, username);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class LoginResponse {\n");

        sb.append("    currentToken: ").append(toIndentedString(currentToken)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object from string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o)
    {
        if (o == null)
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

