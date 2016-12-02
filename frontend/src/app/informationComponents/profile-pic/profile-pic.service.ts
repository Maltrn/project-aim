/**
 * Created by Malte Scheller on 02.11.2016.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {FileID} from "../main/model/fileId";
import {File} from "../main/model/file";



@Injectable()
export class ProfilePictureService
{
    private _fileUrl: string = 'app/file';

    constructor(private http: Http)
    {
    }

    getAllFileIds(): Promise<FileID[]>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.get(this._fileUrl, { headers })
            .toPromise()
            .then(response => response.json().data as FileID[]);
    }

    getSpecificFile(fileId: string): Promise<File>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.get(this._fileUrl + '/' + fileId, {headers})
            .toPromise()
            .then(response => response.json().data as File);
    }

    getPicture(): File[]
    {
        var pictures: File[] = [];
        var file = new File();

        file.name = "fin.gif";
        file.discription = "blabla";
        file.in = "local";
        pictures.push(file);

        file = new File();
        file.name = "fin1234.png";
        file.discription = "blabla";
        file.in = "local";
        pictures.push(file);

        file = new File();
        file.name = "fin.pdf";
        file.discription = "blabla";
        file.in = "local";
        pictures.push(file);

        return pictures;
    }

    private handleError(error: any): Promise<any>
    {
        console.error('An error occurred', error);          // TODO Fehlerbehandlung
        return Promise.reject(error.message || error);
    }

}