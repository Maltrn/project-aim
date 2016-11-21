import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";

@Injectable()
export class UserService {
    private loggedIn = false;

    constructor(private http: Http) {
  //todo      this.loggedIn = !!.getItem('auth_token');
    }

    login(email, password) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http
            .post(
                '/login',
                JSON.stringify({ email, password }),
                { headers }
            )
            .map(res => res.json())
            .map((res) => {
                if (res.success) {
 // todo                   .setItem('auth_token', res.auth_token);
                    this.loggedIn = true;
                }

                return res.success;
            });
    }

    logout() {
 // todo       .removeItem('auth_token');
        this.loggedIn = false;
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}