import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {InfoDTO} from "./model/infoDTO";
import 'rxjs/add/operator/toPromise';

import { UserService } from "../../authentification/user.service";
import {userInfo} from "os";

@Injectable()
export class ProductService
{
    private productUrl: String = '/product';
    private _productInformationDto: InfoDTO;
    private userService: UserService;

    constructor(private http: Http)
    {
    }

    loadMock(): void {
       //this._productInformationDto =  new InfoDTO();
       //this._productInformationDto = this._productInformationDto.mockData();
       //this._productInformationDto.name = "Tolles Mock Produkt";
       //console.log("Mock loaded");
    }

    loadProductInformation(): void
    {
        //this._productInformationDto =  new InfoDTO(); // Notwendig?
        var productnumber = 123;
        this.getProduct(productnumber).then(product => this._productInformationDto = product);

    }

    getProductInformationDto(): InfoDTO
    {
        return this._productInformationDto;
    }

    getProduct(id: number): Promise<InfoDTO> {
        const url = `${this.productUrl}/${id}`;
        return this.http.get(url)    // this.http.get!
            .toPromise()
            .then(response => response.json().data as InfoDTO);
    }

}