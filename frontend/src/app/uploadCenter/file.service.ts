import {Injectable} from "@angular/core";
import {Settings} from "../app.config";
import {BaseService} from "../service";
import {Http, RequestOptions, ResponseContentType} from "@angular/http";
import 'rxjs/Rx';

@Injectable()
export class FileService extends BaseService {

    private fileApiURL: string;

    constructor(private http: Http, private settings: Settings) {

        super();
        this.fileApiURL = this.settings.backendApiBaseUrl + "file";
    }

    public getAllFileIds() {

        let options = new RequestOptions({headers: this.buildHeaders()});

        return this.http.get(this.fileApiURL, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    public getFile(id: string) {

        let options = new RequestOptions({
            headers: this.buildHeaders(),
            responseType: ResponseContentType.Blob
        });

        return this.http.get(this.fileApiURL + "/" + id, options)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    public uploadFile(file) {

        console.log("upload file: ");
        console.log(file);
        console.log("TO: ");
        console.log(this.fileApiURL);

        let formData: FormData = new FormData(),
            xhr: XMLHttpRequest = new XMLHttpRequest();

        formData.append("file", file);

        xhr.open('PUT', this.fileApiURL, true);
        xhr.setRequestHeader('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
        xhr.send(formData);
    }

    public replace(file, fileToReplaceId) {

        let formData: FormData = new FormData(),
            xhr: XMLHttpRequest = new XMLHttpRequest();

        formData.append("file", file);

        xhr.open('PUT', this.fileApiURL + '/' + fileToReplaceId, true);
        xhr.setRequestHeader('Authorization', 'TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
        xhr.send(formData);
    }

    public deleteFile(fileId) {

        let options = new RequestOptions({
            headers: this.buildHeaders(),
        });

        return this.http.delete(this.fileApiURL + '/' + fileId, options)
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }
}
