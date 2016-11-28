/**
 * Created by Malte Scheller on 28.11.16.
 */

export class LoginDTO
{
    private _username: string;
    private _password: string;


    get username(): string
    {
        return this._username;
    }

    set username(value: string)
    {
        this._username = value;
    }

    get password(): string
    {
        return this._password;
    }

    set password(value: string)
    {
        this._password = value;
    }
}