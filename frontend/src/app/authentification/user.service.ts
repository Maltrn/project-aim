import {Injectable} from "@angular/core";
import {Http, Headers, Response, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import "rxjs/add/operator/map";

@Injectable()
export class UserService {

  constructor(private http: Http) {

  }

  /**
   * Sending the user.json credentials to the backend.
   * If the credentials are valid, the response contains a token and user.json data.
   * User data and token will be store in the local storage and "loggedIn" will be set to true.
   * */
  public login() {
    let body = JSON.stringify({"password": "1234", "username": "asd"});
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, method: "post"});

    return this.http.get("https://api.github.com/orgs/SuccessAnalyzer")
      .map((res) => res.json());
  }

  public mockLogin() {
    return {
      "loginResponse": {
        "currentToken": "hluh6gcar39vrtep4repiiii3o",
        "username": "john"
      },
      "vendorInfoId": "sym-telegra",
      "produktInfoIds": [
        "sym-omegaSpecialSolution",
        "sym-asteriskPBX"
      ]
    }
  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
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

/*import {Injectable} from '@angular/core';
 import {Http, URLSearchParams} from '@angular/http';
 import 'rxjs/add/operator/map';

 @Injectable()
 export class GithubService {
 constructor(private http: Http) {
 }

 getOrg(org: string) {
 return this.makeRequest(`orgs/${org}`);
 }

 getReposForOrg(org: string) {
 return this.makeRequest(`orgs/${org}/repos`);
 }

 getRepoForOrg(org: string, repo: string) {
 return this.makeRequest(`repos/${org}/${repo}`);
 }

 private makeRequest(path: string) {
 let params = new URLSearchParams();
 params.set('per_page', '100');

 let url = `https://api.github.com/${ path }`;
 return this.http.get(url, {search: params})
 .map((res) => res.json());
 }
 }
 */
