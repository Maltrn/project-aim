/**
 * Created by Malte Scheller on 02.11.2016.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {FileID} from "../main/fileId";
import {File} from "../main/file";



@Injectable()
export class ProfilePictureService
{
    fileUrl: string = 'app/file';

    constructor(private http: Http)
    {
    }

    getAllFileIds(): Promise<FileID[]>
    {
        return this.http.get(this.fileUrl)
            .toPromise()
            .then(response => response.json().data as FileID[]);
    }

    getSpecificFile(fileId: string): Promise<File>
    {
        return this.http.get(this.fileUrl + '/' + fileId)
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