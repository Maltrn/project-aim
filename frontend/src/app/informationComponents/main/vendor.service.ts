/**
 * Created by Spustin Dallek
 */

import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {InfoDTO} from "./dto/infoDTO";
import 'rxjs/add/operator/toPromise';

import { User } from "../../authentification/model/user";

@Injectable()
export class VendorService
{
    private vendorUrl: String = '/vendor';
    private _vendorInformationDto: InfoDTO;
    private _user: User;

    constructor(private http: Http)
    {
    }

    loadMock(): InfoDTO {
       this._vendorInformationDto =  new InfoDTO();
       this._vendorInformationDto = this._vendorInformationDto.mockData();
       return this._vendorInformationDto;
      // return this._vendorInformationDto.shortDescription;
    }

    /* Muss jedes Mal aufgerufen werden, wenn die Informationen aktualisiert wurden*/
    loadVendorInformation(vendorId: String): Promise<InfoDTO>
    {
        return this.http.get(this.vendorUrl + '/' + vendorId)
            .toPromise()
            .then(response => response.json().data as InfoDTO);
    }

    getVendorInformationDto(): InfoDTO
    {
        return this._vendorInformationDto;
    }

    getVendor(): Promise<InfoDTO> {
        const url = `${this.vendorUrl}/${this._user.vendorInfoId}`;
        return this.http.get(url)    // this.http.get!
            .toPromise()
            .then(response => response.json().data as InfoDTO);
    }
}