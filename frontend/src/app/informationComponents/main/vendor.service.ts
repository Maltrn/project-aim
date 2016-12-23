/**
 * Created by Spustin Dallek
 */

import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {InfoDTO} from "./model/infoDTO";
import 'rxjs/add/operator/toPromise';

import {UserService} from "../../authentification/user.service";

const vendorUrl = 'http://localhost:8080/api/vendor';
const testUrl = 'api/vendor';

@Injectable()
export class VendorService
{
    constructor(private http: Http, private userService: UserService){}

    getVendorInformation(): Promise<InfoDTO>
    {
      let user = JSON.parse(localStorage.getItem('user'));
      let id = user.loginResponse.vendorInfoId;
      let url = `${vendorUrl}/${id}`;
        let headers = new Headers({ 'Content-Type': 'application/json' });
       // let token = this.userService.token;
        //headers.append('Authorization', token);
        let options = new RequestOptions({ headers: headers });

        return this.http.get(testUrl, options)
            .toPromise()
            .then(response => response.json().data as InfoDTO)
            .catch(this.handleError);
    }

    handleError(res: Response): string
    {
        if(res.status == 401)
        {
            alert(res.status);
            return 'Authorisierung fehlgeschlagen';
        }

    }
}
