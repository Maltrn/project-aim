import {Injectable} from "@angular/core";
import {Http, Response, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {Settings} from "../app.config";
import {BaseService} from "../service";

@Injectable()
export class ProductService extends BaseService {

  private productApiURL: string;

  constructor(private _http: Http, private settings: Settings) {
    super();
    this.productApiURL = this.settings.backendApiBaseUrl + "product/";
  }

  /**
   * Returns all products just as id
   * @returns {Observable<R>}
   */
  public getAllProducts(): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    return this._http.get(this.productApiURL, options).map(this.extractData).catch(this.handleError);
  }

  /**
   * Returns one product with the given product id
   * @param id ID of the product
   * @returns {Observable<R>}
   */
  public getProduct(id: string): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    return this._http.get(this.productApiURL + id, options).map(this.extractData).catch(this.handleError);
  }

  /**
   * Returns one product with the given product id
   * @param product Product that should be updated
   * @returns {Observable<R>}
   */
  public updateProduct(product): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    return this._http.put(this.productApiURL + product.id, JSON.stringify(product), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Obsolete, please change the product-info-main.component.ts so this is not needed anymore!!!
   * @returns {[{id: string, name: string, shortDescription: string, longDescription: string, mainPic: string, fileGallery: [string,string,string,string], facts: [{first fact: string},{Roses are red: string}]},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: string, fileGallery: Array, facts: [{diameter: string},{length: string}]},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array},{id: string, name: string, shortDescription: string, longDescription: string, mainPic: null, fileGallery: Array, facts: Array}]}
   */
  public loadMock() {
    return [{
      "id": "sym-telegraAcd",
      "name": "telegra ACD",
      "shortDescription": "ring ring ring Banana Phone",
      "longDescription": "You can dial and press some keys, it's worth it",
      "mainPic": "5857cbb6451f5a489a08fc7d",
      "fileGallery": ["5857cbb6451f5a489a08fc81", "5857cbb6451f5a489a08fc82", "5857cbb6451f5a489a08fc83", "5857cbb6451f5a489a08fc84"],
      "facts": [{"first fact": "This fact ist beautiful!!"}, {"Roses are red": "DUP DUP DUP"}]
    }, {
      "id": "sym-telegraPBX",
      "name": "telegra PBX",
      "shortDescription": "it great and long",
      "longDescription": "experience a great cable",
      "mainPic": "5857cbb6451f5a489a08fc7e",
      "fileGallery": [],
      "facts": [{"diameter": "Holy Moly - this cable is perfect for you!"}, {"length": "You won't see the end coming"}]
    }, {
      "id": "sym-omegaSpecialSolution",
      "name": "Omega Special-Solution",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-omegaTwo",
      "name": "Omega-Two",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-omegaOne",
      "name": "Omega-One",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-omegaAll",
      "name": "Omega-All",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-asteriskPBX",
      "name": "Asterisk, die freie PBX",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-deltaTelekommunikationsloesungMax",
      "name": "Delta-TelekommunikationsloesungMax",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-deltaTelekommunikationsloesungPlus",
      "name": "Delta-TelekommunikationslösungPlus",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-effiMonitoring",
      "name": "Call Center Monitor",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-pbxSolutions",
      "name": "PBX-Solutions",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-pbxPowerSolutions",
      "name": "PBX-PowerSolutions",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-betaStandard",
      "name": "Beta-Standard",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-betaPremium",
      "name": "Beta-Premium",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-betaBasics",
      "name": "Beta-Basics",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-kmuSpezial",
      "name": "KMU-Spezial",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-bankingSpezial",
      "name": "Banking-Spezial",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-autoSpezial",
      "name": "Auto-Spezial",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-alphaPBXPremium",
      "name": "",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-alphaPBX",
      "name": "Alpha-PBX",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-alphaACDManagementReports",
      "name": "Alpha-ACD-ManagementReports",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }, {
      "id": "sym-alphaACD",
      "name": "Alpha-ACD",
      "shortDescription": "",
      "longDescription": "",
      "mainPic": null,
      "fileGallery": [],
      "facts": []
    }];
  }

  /**
   * Obsolete, please change the product-info-main.component.ts so this is not needed anymore!!!
   * @returns {{name: string}}
   */
  public getProductInformationDto() {
    return {"name": ""};
  }
}
