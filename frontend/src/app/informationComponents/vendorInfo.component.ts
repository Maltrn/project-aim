import {Component} from "@angular/core";
import {VendorService} from "./vendor.service";
import {UserService} from "../authentication/user.service";
import {Settings} from "../app.config";


@Component
({
  selector: 'vendor-info',
  templateUrl: './vendorInfo.component.html'
})

export class VendorInfo {

  private vendor: any = {
    "id": "",
    "name": "",
    "shortDescription": "",
    "longDescription": "",
    "mainPic": "",
    "fileGallery": [],
    "facts": []
  };

  private description: string;

  private shortDescription: any;

  private renderShortDescription: boolean;

  constructor(private vendorService: VendorService, private userService: UserService, private settings: Settings) {
    this.renderShortDescription = false;
  }

  ngOnInit(): void {
    this.loadVendor("sym-telegra");
  }

  private loadVendor(id: string) {
    this.vendorService.getVendor(id).subscribe(
      data => {
        this.vendor = data;
        this.renderShortDescription = true;
        this.updateShortDescriptionText();
      },
      error => {
        console.log("ERROR in REST API");
        if (error.indexOf("401") !== -1) {
          this.userService.logout();
        }
      });
  }

  // events
  public onChange(e: any): void {
    this.updateShortDescriptionText();
  }

  public onReady(e: any): void {
    this.updateShortDescriptionText();
  }

  public onFocus(e: any): void {
    this.updateShortDescriptionText();
  }

  public onBlur(e: any): void {
    this.updateShortDescriptionText();
  }

  private updateShortDescriptionText(): void {
    this.description = 'Maximale Anzahl der Zeichen: ';
    let actualLength = this.sanitizedTextLenght(this.vendor.shortDescription);
    if (actualLength > this.settings.shortDescriptionMaxLength) {
      this.description += "<span class=\"text-danger\">" + actualLength + "</span>";
    } else {
      this.description += actualLength;
    }
    this.description += '/' + this.settings.shortDescriptionMaxLength;
  }

  private sanitizedTextLenght(text: string): number {
    let sanitized: string = text.replace(/(<(?:.|\n)*?>|(?:\r\n|\r|\n))/gm, '');
    return sanitized.length;
  }
}
