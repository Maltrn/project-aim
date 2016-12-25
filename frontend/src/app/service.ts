import {Response, Headers} from "@angular/http";
import {Observable} from "rxjs";

export abstract class ServiceBase {

  protected buildHeaders(): Headers {
    let headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
    return headers;
  }

  protected extractData(res: Response) {
    let body = res.json();
    return body;
  }

  protected handleError(error: Response | any) {
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
