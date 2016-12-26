import {Component} from "@angular/core";
import {VendorService} from "./vendor.service";
import {UserService} from "../authentication/user.service";


@Component
({
  selector: 'vendor-info',
  templateUrl: './vendorInfo.component.html'
})

export class VendorInfo {

  private vendor;

  constructor(private vendorService: VendorService, private userService: UserService) {

  }

  ngOnInit(): void {
    this.loadVendor("sym-telegra");
  }

  private loadVendor(id: string) {
    this.vendorService.getVendor(id).subscribe(
      data => this.vendor = data,
      error => {
        console.log("ERROR in REST API");
        if (error.indexOf("401") !== -1) {
          this.userService.logout();
        }
      });
  }
}
