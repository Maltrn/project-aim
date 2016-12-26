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

  private shortDescriptionTag: string;

  private longDescriptionTag: string;

  private renderDescriptions: boolean;

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
        this.renderDescriptions = true;
        this.updateDescriptionsTag();
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
    this.updateDescriptionsTag();
  }

  public onReady(e: any): void {
    this.updateDescriptionsTag();
  }

  public onFocus(e: any): void {
    this.updateDescriptionsTag();
  }

  public onBlur(e: any): void {
    this.updateDescriptionsTag();
  }

  private updateDescriptionsTag(): void {
    this.shortDescriptionTag = 'Maximale Anzahl der Zeichen: ';
    let actualShortDescriptionLength = this.sanitizedTextLength(this.vendor.shortDescription);

    this.longDescriptionTag = 'Maximale Anzahl der Zeichen: ';
    let actualLongDescriptionLength = this.sanitizedTextLength(this.vendor.longDescription);

    if (actualShortDescriptionLength > this.settings.shortDescriptionMaxLength) {
      this.shortDescriptionTag += "<span class=\"text-danger\">" + actualShortDescriptionLength + "</span>";
    } else {
      this.shortDescriptionTag += actualShortDescriptionLength;
    }
    this.shortDescriptionTag += '/' + this.settings.shortDescriptionMaxLength;

    if (actualLongDescriptionLength > this.settings.longDescriptionsMaxLength) {
      this.longDescriptionTag += "<span class=\"text-danger\">" + actualLongDescriptionLength + "</span>";
    } else {
      this.longDescriptionTag += actualLongDescriptionLength;
    }
    this.longDescriptionTag += '/' + this.settings.longDescriptionsMaxLength;
  }

  private sanitizedTextLength(text: string): number {
    let sanitized: string = text.replace(/(<(?:.|\n)*?>|(?:\r\n|\r|\n))/gm, '');
    return sanitized.length;
  }
}
