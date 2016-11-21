/**
 * Created by Malte Scheller on 18.11.16.
 */

import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {InfoDTO} from "./dto/infoDTO";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class VendorService
{
    private vendorUrl: String = '/vendor'

    constructor(private http: Http)
    {
    }

    getVendorInformation(vendorId: String): Promise<InfoDTO>
    {
        return this.http.get(this.vendorUrl + '/' + vendorId)
            .toPromise()
            .then(response => response.json().data as InfoDTO);
    }
}