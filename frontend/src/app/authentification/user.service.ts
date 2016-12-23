import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import "rxjs/add/operator/map";
import {Configs} from "../app.config";

@Injectable()
export class UserService {

  constructor(private http: Http) {

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

    return this.http.post(Configs.BACKEND_URL + "login", body, options)
      .map((res) => res.json());
  }
}
