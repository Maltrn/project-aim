/**
 * Created by Malte Scheller on 28.11.16.
 */

export class LoginResponse
{
    private _currentToken: string;
    private _username: string;


    get currentToken(): string
    {
        return this._currentToken;
    }

    set currentToken(value: string)
    {
        this._currentToken = value;
    }

    get username(): string
    {
        return this._username;
    }

    set username(value: string)
    {
        this._username = value;
    }
}