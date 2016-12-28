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

  private maxFactsEntriesTag: string;

  private renderDescriptions: boolean;

  private currentFactDescription: string;

  private toggleCurentFactEdit: boolean;

  private newFactName: string;

  private newFactDescription: string;

  private maxFileGalleryEntriesTag: string;

  constructor(private vendorService: VendorService, private userService: UserService, private settings: Settings) {
    this.renderDescriptions = false;
    this.newFactName = "";
    this.newFactDescription = "";
    this.currentFactDescription = "";
    this.maxFileGalleryEntriesTag = "";
    this.toggleCurentFactEdit = false;
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
        this.updateMaxFactsEntriesTag();
        this.updateMaxFileGalleryTag();
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

  private updateMaxFactsEntriesTag(): void {
    this.maxFactsEntriesTag = 'Maximale Anzahl der Einträge: ';
    if (this.vendor.facts.length > this.settings.featureTableMaxEntries) {
      this.maxFactsEntriesTag += "<span class=\"text-danger\">" + this.vendor.facts.length + "</span>";
    } else {
      this.maxFactsEntriesTag += this.vendor.facts.length;
    }
    this.maxFactsEntriesTag += '/' + this.settings.featureTableMaxEntries;
  }

  private updateMaxFileGalleryTag(): void {
    this.maxFileGalleryEntriesTag = 'Maximale Anzahl der Dateien: ';
    if (this.vendor.fileGallery.length > this.settings.fileGalleryMaxEntries) {
      this.maxFileGalleryEntriesTag += "<span class=\"text-danger\">" + this.vendor.fileGallery.length + "</span>";
    } else {
      this.maxFileGalleryEntriesTag += this.vendor.fileGallery.length;
    }
    this.maxFileGalleryEntriesTag += '/' + this.settings.fileGalleryMaxEntries;
  }

  private sanitizedTextLength(text: string): number {
    let sanitized: string = text.replace(/(<(?:.|\n)*?>|(?:\r\n|\r|\n))/gm, '');
    return sanitized.length;
  }

  private pushFactDown(fact: any): void {
    if (this.vendor.facts != null && this.vendor.facts.length > 0) {
      let oldIndex: number = this.vendor.facts.indexOf(fact);
      let newIndex: number = (oldIndex + 1);
      if (newIndex < this.vendor.facts.length) {
        let originalFact = this.vendor.facts[newIndex];
        this.vendor.facts[newIndex] = this.vendor.facts[oldIndex];
        this.vendor.facts[oldIndex] = originalFact;
      }
    }
  }

  private pushFactUp(fact: any): void {
    if (this.vendor.facts != null && this.vendor.facts.length > 0) {
      let oldIndex: number = this.vendor.facts.indexOf(fact);
      let newIndex: number = (oldIndex - 1);
      if (newIndex >= 0) {
        let originalFact = this.vendor.facts[newIndex];
        this.vendor.facts[newIndex] = this.vendor.facts[oldIndex];
        this.vendor.facts[oldIndex] = originalFact;
      }
    }
  }

  private addFact(): void {
    if (this.newFactName != "" && this.newFactDescription != "") {
      let newFact = {};
      newFact[this.newFactName] = this.newFactDescription;
      this.vendor.facts.push(newFact);
      this.newFactName = "";
      this.newFactDescription = "";
      this.updateMaxFactsEntriesTag();
    }
  }

  private removeFact(fact) {
    let index: number = this.vendor.facts.indexOf(fact, 0);
    if (index > -1) {
      this.vendor.facts.splice(index, 1);
      this.updateMaxFactsEntriesTag();
    }
  }

  private editFact(fact) {
    let value: string = fact[Object.getOwnPropertyNames(fact)[0]];
    fact.show = !fact.show;
    this.currentFactDescription = value;
  }

  private saveFact(fact) {
    let key: string = Object.getOwnPropertyNames(fact)[0];
    fact.show = !fact.show;
    fact[key] = this.currentFactDescription;
  }

  private pushFileUp(file) {
    if (this.vendor.fileGallery != null && this.vendor.fileGallery.length > 0) {
      let oldIndex: number = this.vendor.fileGallery.indexOf(file);
      let newIndex: number = (oldIndex - 1);
      if (newIndex >= 0) {
        let originalFact = this.vendor.fileGallery[newIndex];
        this.vendor.fileGallery[newIndex] = this.vendor.fileGallery[oldIndex];
        this.vendor.fileGallery[oldIndex] = originalFact;
      }
    }
  }

  private pushFileDown(file) {
    if (this.vendor.fileGallery != null && this.vendor.fileGallery.length > 0) {
      let oldIndex: number = this.vendor.fileGallery.indexOf(file);
      let newIndex: number = (oldIndex + 1);
      if (newIndex < this.vendor.fileGallery.length) {
        let originalFact = this.vendor.fileGallery[newIndex];
        this.vendor.fileGallery[newIndex] = this.vendor.fileGallery[oldIndex];
        this.vendor.fileGallery[oldIndex] = originalFact;
      }
    }
  }

  private removeFile(file) {
    let index: number = this.vendor.fileGallery.indexOf(file, 0);
    if (index > -1) {
      this.vendor.fileGallery.splice(index, 1);
      this.updateMaxFileGalleryTag();
    }
  }
}
