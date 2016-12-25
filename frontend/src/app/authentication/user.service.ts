import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import "rxjs/add/operator/map";
import {Settings} from "../app.config";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable()
export class UserService {

  private _isLoggedIn: boolean;

  constructor(private http: Http, private router: Router, private settings: Settings) {
    this._isLoggedIn = localStorage.getItem('user') != null && localStorage.getItem('user') != "{}";
  }

  /**
   * Sending the user.json credentials to the backend.
   * If the credentials are valid, the response contains a token and user.json data.
   * User data and token will be store in the local storage and "loggedIn" will be set to true.
   * */
  public login(email: string, password: string) {
    let body = JSON.stringify({"password": password, "username": email});
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    return this.http.post(this.settings.backendApiBaseUrl + "login", body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Call this function when login status changes
   */
  public changeLoginStatus(status: boolean) {
    this._isLoggedIn = status;
  }

  /**
   * Call this to log the user out
   */
  public logout(): void {
    localStorage.removeItem('user');
    this._isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  private extractData(res: Response) {
    let body = res.json();
    return body;
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
