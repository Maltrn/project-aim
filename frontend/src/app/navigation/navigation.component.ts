import {Component} from "@angular/core";
import {UserService} from "../authentication/user.service";
import {VendorService} from "../informationComponents/vendor.service";

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
    this.loadVendors();
    this.isCollapsed = true;
  }

  public collapsed(event: any): void {
    console.log(event);
  }

  public expanded(event: any): void {
    console.log(event);
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
