import {Component} from "@angular/core";
import {UserService} from "../authentication/user.service";
import {VendorService} from "../vendor/vendor.service";

@Component
({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
})
export class NavigationComponent {

  private vendors: any;
  private products: any;
  public isCollapsed: boolean;

  constructor(private userService: UserService, private vendorService: VendorService) {
    if (this.userService.isLoggedIn) {
      this.loadVendors();
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

  private loadVendors() {
    this.vendorService.getVendors().subscribe(
      data => {
        this.vendors = data;
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
