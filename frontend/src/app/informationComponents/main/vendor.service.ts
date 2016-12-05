/**
 * Created by Spustin Dallek
 */

import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {InfoDTO} from "./dto/infoDTO";
import 'rxjs/add/operator/toPromise';

import { UserService } from "../../authentification/user.service";
import {userInfo} from "os";

@Injectable()
export class VendorService
{
    private vendorUrl: String = '/vendor';
    private _vendorInformationDto: InfoDTO;
    private userService: UserService;

    constructor(private http: Http)
    {
    }

    loadMock(): void {
       this._vendorInformationDto =  new InfoDTO();
       this._vendorInformationDto = this._vendorInformationDto.mockData();
       console.log("Mock loaded");
    }

    loadVendorInformation(): void
    {
        //this._vendorInformationDto =  new InfoDTO(); // Notwendig?
        this.getVendor().then(vendor => this._vendorInformationDto = vendor);

    }

    getVendorInformationDto(): InfoDTO
    {
        return this._vendorInformationDto;
    }

    getVendor(): Promise<InfoDTO> {
        const url = `${this.vendorUrl}/${this.userService.user.vendorInfoId}`;
        return this.http.get(url)    // this.http.get!
            .toPromise()
            .then(response => response.json().data as InfoDTO);
    }

}