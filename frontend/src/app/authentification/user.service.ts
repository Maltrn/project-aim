import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {LoginDTO} from "./model/loginDTO";
import {User} from "./model/user";

const loginUrl = '/login';

@Injectable()
export class UserService
{
    private loggedIn;       // for CanActivate guard
    private _user: User;

    constructor(private http: Http)
    {
        this.loggedIn = false;
        this._user = JSON.parse(localStorage.getItem('aim_user'));
    }

    /**
    * Sending the user credentials to the backend.
    * If the credentials are valid, the response contains a token and user data.
    * User data and token will be store in the local storage and "loggedIn" will be set to true.
    * */
    login(loginDto: LoginDTO): Promise<any>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(loginUrl, JSON.stringify(loginDto), {headers})
                    .toPromise()
                    .then(res => this._user = res.json().data as User)
                    .then(res =>
                    {
                        localStorage.setItem('aim_token', res.loginResponse.currentToken);
                        localStorage.setItem('aim_user', res.loginResponse.currentToken);
                    })
                    .then(res =>
                    {
                        if(res)
                        {
                            this.loggedIn = true;
                        }

                    })
                    .catch(this.handleError);
    }

    /**
     * Delete the user data and token from local storage and set loggedIn to false.
     */
    logout()
    {
        localStorage.removeItem('aim_user');
        localStorage.removeItem('aim_token');
        this.loggedIn = false;
    }

    isLoggedIn()
    {
        return this.loggedIn;
    }

    private handleError(error: Response | any): Promise<any>
    {
        let errMsg: string;

        if(error instanceof Response)
        {
            let resErr: Response = error;
            var statusCode = resErr.status;
            errMsg = `${resErr.status} - ${resErr.statusText || ''} ${resErr}`;
        }
        else
        {
            errMsg = error.toString();
        }

        console.error('An error occurred', errMsg);
        return Promise.reject(statusCode);
    }


    get user(): User
    {
        return this._user;
    }
}