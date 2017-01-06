import {Component} from "@angular/core";
import {UserService} from "../authentication/user.service";
import {VendorService} from "../vendor/vendor.service";
import {ProductService} from "../product/product.service"
import {forEach} from "@angular/router/src/utils/collection";

@Component
({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
})
export class NavigationComponent {

  private vendors: any;
  private products: any[] = [];
  private productNames: Array<String>;
  // private productNames: String[];
  public isCollapsed: boolean;

  constructor(private userService: UserService, private vendorService: VendorService, private productService: ProductService) {
    if (this.userService.isLoggedIn) {
      this.loadVendors();
      this.loadProducts();
    }
    this.isCollapsed = true;
  }

  public collapsed(event: any): void {
    if (this.userService.isLoggedIn) {
      this.loadVendors();
    }
  }

  public expanded(event: any): void {
    if (this.userService.isLoggedIn) {
      this.loadVendors();
    }
  }

  public loadVendors(): void {
    let vendorId: string = JSON.parse(localStorage.getItem('user')).vendorInfoId;

    this.vendorService.getVendor(vendorId).subscribe(
      data => {
        this.vendors = [data];
      },
      error => {
        console.log("ERROR in REST API");
        console.log(error);
        if (error.indexOf("401") !== -1) {
          this.userService.logout();
        }
      });
  }

  public loadProducts(): void{
    let productIds: string = JSON.parse(localStorage.getItem('user')).produktInfoIds;
    for (var _i = 0; _i < productIds.length; _i++) {
      this.productService.getProduct(productIds[_i]).subscribe(
          data => {
            this.products.push(data);
          },
          error => {
            console.log("ERROR in REST API");
            console.log(error);
            if (error.indexOf("401") !== -1) {
              this.userService.logout();
            }
          });
    }
  }
}
