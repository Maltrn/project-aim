import {Injectable} from "@angular/core";
import {Settings} from "../app.config";
import {Http, Headers, RequestOptions} from "@angular/http";
import 'rxjs/Rx';
import {UploadedFile} from "./uploadedFile";
import {ServiceBase} from "../service";

@Injectable()
export class FileService extends ServiceBase {

  private fileApiURL: string;

  constructor(private _http: Http, private settings: Settings) {
    super();
    this.fileApiURL = this.settings.backendApiBaseUrl + "file";
  }

  public getAllFiles() {
    let result: UploadedFile[];
    let options = new RequestOptions({headers: this.buildHeaders()});
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
}
