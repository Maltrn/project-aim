/**
 * Created by Malte Scheller on 02.11.2016.
 */
import {Injectable} from "@angular/core";
import { Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';


@Injectable()
export class ProfilePictureService
{
    fileUrl: string = '/file';

    mockIds: string[] = ['klaus.pdf','prof.jpg','sda2.jpg','doc.pdf','jpg.jpg','jpg.pdf'];

    constructor(private http: Http)
    {
    }

    /*Returns an Array of File-IDs*/
    getPictureIds(): Promise<string[]>
    {
        return this.http.get(this.fileUrl)
            .toPromise()
            .then(response => response.json().data as string[])
            .catch(this.handleError);
    }

    getPictureIdsOff(): Promise<string[]>
    {
        return Promise.resolve(this.mockIds);
    }

    private handleError(error: any): Promise<any>
    {
        console.error('An error occurred', error);          // TODO Fehlerbehandlung
        return Promise.reject(error.message || error);
    }

}