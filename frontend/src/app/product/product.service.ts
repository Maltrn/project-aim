import {Injectable} from "@angular/core";
import {Http, Response, RequestOptions} from "@angular/http";
import {BaseService} from "../service";
import {Observable} from "rxjs";
import {Settings} from "../app.config";

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

}
