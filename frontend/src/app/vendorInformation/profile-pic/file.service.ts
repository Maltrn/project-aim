/**
 * Created by Maltron on 02.11.2016.
 */
import {Injectable} from "@angular/core";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class FileService
{
    picIdArray: string[] = ["pic1, pic2, pic3"];

    /*Returns an Array of Picture-IDs*/
    getPictureIds(): string[]
    {
        return this.picIdArray;
    }
}