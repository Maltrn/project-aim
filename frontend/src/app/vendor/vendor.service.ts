import {Injectable} from "@angular/core";
import {Http, Response, RequestOptions} from "@angular/http";
import {BaseService} from "../service";
import {Observable} from "rxjs";
import {Settings} from "../app.config";

@Injectable()
export class VendorService extends BaseService {

  private vendorApiURL: string;

  constructor(private _http: Http, private settings: Settings) {
    super();
    this.vendorApiURL = this.settings.backendApiBaseUrl + "vendor/";
  }

  /**
   * Returns observable with all vendors the user is allowed to access.
   * @returns {Observable<R>}
   */
  public getVendors(): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    return this._http.get(this.vendorApiURL, options).map(this.extractData).catch(this.handleError);
  }

  /**
   * Returns observable with a specific vendor based on the given id
   * @param id ID of the vendor
   * @returns {Observable<R>}
   */
  public getVendor(id: string): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    return this._http.get(this.vendorApiURL + id, options).map(this.extractData).catch(this.handleError);
  }

  /**
   * Returns observable with the response of a put request to update a given vendor.
   * @param vendor Vendor object with the updated values
   * @returns {Observable<R>}
   */
  public updateVendor(vendor): Observable<Response> {
    let options = new RequestOptions({headers: this.buildHeaders()});
    let body = JSON.stringify(vendor);
    console.log("Body: " + body);
    return this._http.put(this.vendorApiURL, body, options).map(this.extractData).catch(this.handleError);
  }

}
