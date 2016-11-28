import {Injectable} from '@angular/core';
import {Http, Headers, Response} from '@angular/http';
import {Observable} from "rxjs";

@Injectable()
export class UserService
{
    private loggedIn;
    private LOGINURL = '/login'

    constructor(private http: Http)
    {
        this.loggedIn = false;
        this.loggedIn = !!localStorage.getItem('auth_token');
    }

    login(email, password): Observable<Response>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.post(this.LOGINURL, JSON.stringify({email, password}),{headers})
            .map(res => res.json())
            .map((res) =>
            {
                if(res.success)
                {
                    this.loggedIn = true;
                }

                return res.success;
            })
    }

    logout()
    {
        localStorage.removeItem('auth_token');
        this.loggedIn = false;
    }

    isLoggedIn()
    {
        return this.loggedIn;
    }
}