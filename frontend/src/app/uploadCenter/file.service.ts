import {Injectable} from "@angular/core";
import {Settings} from "../app.config";
import {Http, Headers, RequestOptions, RequestMethod, ResponseContentType} from "@angular/http";
import 'rxjs/Rx';
import {UploadedFile} from "./uploadedFile";
import {BaseService} from "../service";
import {Observable} from "rxjs";

@Injectable()
export class FileService extends BaseService {

    private fileApiURL: string;

    constructor(private _http: Http, private settings: Settings) {

        super();
        this.fileApiURL = this.settings.backendApiBaseUrl + "file";
    }

    public getAllFileIds() {

        let result: UploadedFile[];
        let options = new RequestOptions({headers: this.buildHeaders()});

        return this._http.get(this.fileApiURL, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getFile(id: string) {

        let options = new RequestOptions({
            headers: this.buildHeaders(),
            responseType: ResponseContentType.Blob
        });

        return this._http.get(this.fileApiURL + "/" + id, options)
            .map(response => response);
    }

    public uploadFile(file) {

        // funktioniert nicht :(
        /*
         let formData: FormData = new FormData();

         formData.append("file", file);
         let headers = new Headers();
         headers.append('Content-Type', 'multipart/form-data');
         headers.append('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
         let options = new RequestOptions({headers: headers});

         this._http.put(this.fileApiURL, formData, options)
         .map(res => res);
         */

        let formData: FormData = new FormData(),
            xhr: XMLHttpRequest = new XMLHttpRequest();

        formData.append("file", file);

        xhr.open('PUT', this.fileApiURL, true);
        xhr.setRequestHeader('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
        xhr.send(formData);
    }

    public replace(file, fileToReplaceId) {

        /*
         let headers = new Headers();
         headers.append('Content-Type', 'application/json');

         return this._http.put(this.fileApiURL + "/" + file.id, JSON.stringify(file), {headers: headers})
         .map(res => res.json());
         */

        let formData: FormData = new FormData(),
            xhr: XMLHttpRequest = new XMLHttpRequest();

        formData.append("file", file);

        xhr.open('PUT', this.fileApiURL + '/' + fileToReplaceId, true);
        xhr.setRequestHeader('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
        xhr.send(formData);
    }
}
