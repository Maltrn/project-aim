/**
 * Created by Malte Scheller on 02.11.2016.
 */
import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {FileID} from "../main/model/fileId";
import {File} from "../main/model/file";

const fileUrl: string = 'app/file';

@Injectable()
export class ProfilePictureService
{
    constructor(private http: Http){}

    getAllFileIds(): Promise<FileID[] | number>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.get(fileUrl, { headers })
            .toPromise()
            .then(response => response.json().data as FileID[])
            .catch(this.handleError);
    }

    getSpecificFile(fileId: string): Promise<File>
    {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        return this.http.get(fileUrl + '/' + fileId, {headers})
            .toPromise()
            .then(response => response.json().data as File)
            .catch(this.handleError);
    }


    private handleError(error: Response | any)
    {
        let errMsg: string;

        if(error instanceof Response)
        {
            let resErr: Response = error;
            var statusCode = resErr.status;
            errMsg = `${resErr.status} - ${resErr.statusText || ''} ${resErr}`;
        }
        else
        {
            errMsg = error.toString();
        }

        console.error('An error occurred in Profile-Pic-Service', errMsg);
        return Promise.reject(statusCode);
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
}