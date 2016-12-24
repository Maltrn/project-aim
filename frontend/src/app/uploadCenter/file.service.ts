import {Injectable} from "@angular/core";
import {Settings} from "../app.config";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import 'rxjs/Rx';
import {UploadedFile} from "./uploadedFile";
import {Observable} from "rxjs";

@Injectable()
export class FileService {

  private fileApiURL: string;
  private headers: Headers;

  constructor(private _http: Http, private settings: Settings) {
    this.fileApiURL = this.settings.backendApiBaseUrl + "file";
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', 'TOKEN gcmdjt9evcjpv9c994l5ltqe76');
  }

  public getAllFiles() {
    let result: UploadedFile[];
    let options = new RequestOptions({headers: this.headers});
    return this._http.get(this.fileApiURL, options).map(this.extractData).catch(this.handleError);
  }

  public getFile(id: string) {
    return this._http.get(this.fileApiURL + "/" + id).map(res => res.json());
  }

  public uploadFile(file: UploadedFile) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this._http.put(this.fileApiURL, JSON.stringify(file), {headers: headers})
      .map(res => res.json());
  }

  public replace(file: UploadedFile) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this._http.put(this.fileApiURL + "/" + file.id, JSON.stringify(file), {headers: headers})
      .map(res => res.json());
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || {};
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
