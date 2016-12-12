import {Injectable} from '@angular/core';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import {LoginDTO} from "./model/loginDTO";
import {Configs} from "../app.config";
import {Observable} from "rxjs";


const errMsg401 = 'Benutzername existiert nicht oder das angegebene Passwort ist falsch';
const errMsg503 = 'Login nicht erfolgreich, da keine Anbieter und/oder Produktinformationen abgerufen werden können.';
const unknownErrMsg = 'Unbekannter Fehler';

@Injectable()
export class UserService
{

  constructor(private http: Http)
  {

  }

  /**
   * Sending the user.json credentials to the backend.
   * If the credentials are valid, the response contains a token and user.json data.
   * User data and token will be store in the local storage and "loggedIn" will be set to true.
   * */
  public login()
  {
    let body = JSON.stringify({"password": "1234", "username": "asd"});
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, method: "post"});

    console.log(Configs.BACKEND_URL + "login");
    console.log(body);

    this.http.post('http://aim.gartsy.de/api/login', body, options);

    return this.http.post('http://aim.gartsy.de/api/login', body, options)
      .map(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: Response | any)
  {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if(error instanceof Response)
    {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else
    {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
