import {Injectable} from '@angular/core';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import {LoginRequest} from "./model/loginRequest";
import {User} from "./model/user";

const loginUrl = 'http://localhost:8080/api/login';
const mockUrl = 'api/auth';

const errMsg401 = 'Benutzername existiert nicht oder das angegebene Passwort ist falsch';
const errMsg503 = 'Login nicht erfolgreich, da keine Anbieter und/oder Produktinformationen abgerufen werden können.';
const unknownErrMsg = 'Unbekannter Fehler';

@Injectable()
export class UserService
{
    private _loggedIn: boolean;       // for CanActivate guard
    private _user: User;

    constructor(private http: Http)
    {
        this._loggedIn = false;

        //this._user.vendorInfoId = JSON.parse(localStorage.getItem('aim_vendorId'));
    }

    /**
     * Sending the user.json credentials to the backend.
     * If the credentials are valid, the response contains a token and user.json data.
     * User data and token will be store in the local storage and "loggedIn" will be set to true.
     * */
    login(loginRequest: LoginRequest): void
    {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        this.http
            .post(loginUrl, JSON.stringify(loginRequest), options)
            .toPromise()
            .then(res =>
            {
                this._user = res.json().data as User;
            })
            .then(() =>
            {
                localStorage.setItem('aim_token', this._user.loginResponse.currentToken);
                localStorage.setItem('aim_user', this._user.loginResponse.currentToken);

                console.log(this._user);
            })
            .then(res =>
            {
                if(res)
                {
                    this._loggedIn = true;
                }

            }).catch(this.handleError);
    }

    /**
     * Delete the user.json data and token from local storage and set loggedIn to false.
     */
    logout()
    {
        localStorage.removeItem('aim_token');
        localStorage.removeItem('aim_vendorId');
        this._loggedIn = false;
    }

    isLoggedIn(): boolean
    {
        return this._loggedIn;
    }

    get user(): User
    {
        return this._user;
    }

    get token(): string
    {
        return localStorage.getItem('aim_token');
    }

    handleError(error: any): void
    {
        let errMsg: string;

        if(error instanceof Response)
        {
            let resErr: Response = error;
            errMsg = `${resErr.status} - ${resErr.statusText || ''} ${resErr}`;

            switch(resErr.status)
            {
                case 401:
                    errMsg = errMsg401;
                    break;
                case 503:
                    errMsg = errMsg503;
                    break;
                default:
                    errMsg = unknownErrMsg;
            }
        }
        else
        {
            errMsg = error.toString();
        }

        console.error('An error occurred in User-Service', errMsg);

        if((errMsg !== '') && (errMsg !== null))      //TODO ErrorMsg direkt im Eingabebereich anzeigen. PopUp evtl.
        {
            console.log(errMsg);
            console.log("Detail: " + error);
        }
        else
            console.log('Fehler nicht beschrieben');  //TODO Beschreibung anpassen
    }
}