/**
 * Created by dustinspallek on 05.01.17.
 */
import {Component, OnInit} from "@angular/core";
import {ProductService} from "./product.service";
import {UserService} from "../authentication/user.service";
import {Settings} from "../app.config";
import {ActivatedRoute} from "@angular/router";


@Component
({
    selector: 'product-info',
    templateUrl: './productInfo.component.html'
})
export class ProductInfo implements OnInit {

    private productId: string;

    private product: any = {
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

    private error: string;

    private info: string;

    constructor(private productService: ProductService, private userService: UserService, private settings: Settings, private route: ActivatedRoute) {

    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.productId = params['productId'];
            this.renderDescriptions = false;
            this.newFactName = "";
            this.newFactDescription = "";
            this.currentFactDescription = "";
            this.maxFileGalleryEntriesTag = "";
            this.error = "";
            this.info = "";
            this.toggleCurentFactEdit = false;
            if (this.productId) {
                this.loadProduct(this.productId);
            }
        });
    }

    private loadProduct(id: string) {
            this.productService.getProduct(id).subscribe(
                data => {
                    this.product = data;
                    this.renderDescriptions = true;
                    this.updateDescriptionsTag();
                    this.updateMaxFactsEntriesTag();
                    this.updateMaxFileGalleryTag();
                },
                error => {
                    console.log("ERROR in REST API");
                    console.log(error);
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
        let actualShortDescriptionLength = this.sanitizedTextLength(this.product.shortDescription);

        this.longDescriptionTag = 'Maximale Anzahl der Zeichen: ';
        let actualLongDescriptionLength = this.sanitizedTextLength(this.product.longDescription);

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
        if (this.product.facts.length > this.settings.featureTableMaxEntries) {
            this.maxFactsEntriesTag += "<span class=\"text-danger\">" + this.product.facts.length + "</span>";
        } else {
            this.maxFactsEntriesTag += this.product.facts.length;
        }
        this.maxFactsEntriesTag += '/' + this.settings.featureTableMaxEntries;
    }

    private updateMaxFileGalleryTag(): void {
        this.maxFileGalleryEntriesTag = 'Maximale Anzahl der Dateien: ';
        if (this.product.fileGallery.length > this.settings.fileGalleryMaxEntries) {
            this.maxFileGalleryEntriesTag += "<span class=\"text-danger\">" + this.product.fileGallery.length + "</span>";
        } else {
            this.maxFileGalleryEntriesTag += this.product.fileGallery.length;
        }
        this.maxFileGalleryEntriesTag += '/' + this.settings.fileGalleryMaxEntries;
    }

    private sanitizedTextLength(text: string): number {
        let sanitized: string = text.replace(/(<(?:.|\n)*?>|(?:\r\n|\r|\n))/gm, '');
        return sanitized.length;
    }

    private pushFactDown(fact: any): void {
        if (this.product.facts != null && this.product.facts.length > 0) {
            let oldIndex: number = this.product.facts.indexOf(fact);
            let newIndex: number = (oldIndex + 1);
            if (newIndex < this.product.facts.length) {
                let originalFact = this.product.facts[newIndex];
                this.product.facts[newIndex] = this.product.facts[oldIndex];
                this.product.facts[oldIndex] = originalFact;
            }
        }
    }

    private pushFactUp(fact: any): void {
        if (this.product.facts != null && this.product.facts.length > 0) {
            let oldIndex: number = this.product.facts.indexOf(fact);
            let newIndex: number = (oldIndex - 1);
            if (newIndex >= 0) {
                let originalFact = this.product.facts[newIndex];
                this.product.facts[newIndex] = this.product.facts[oldIndex];
                this.product.facts[oldIndex] = originalFact;
            }
        }
    }

    private addFact(): void {
        if (this.newFactName != "" && this.newFactDescription != "") {
            let newFact = {};
            newFact[this.newFactName] = this.newFactDescription;
            this.product.facts.push(newFact);
            this.newFactName = "";
            this.newFactDescription = "";
            this.updateMaxFactsEntriesTag();
        }
    }

    private removeFact(fact) {
        let index: number = this.product.facts.indexOf(fact, 0);
        if (index > -1) {
            this.product.facts.splice(index, 1);
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
        if (this.product.fileGallery != null && this.product.fileGallery.length > 0) {
            let oldIndex: number = this.product.fileGallery.indexOf(file);
            let newIndex: number = (oldIndex - 1);
            if (newIndex >= 0) {
                let originalFact = this.product.fileGallery[newIndex];
                this.product.fileGallery[newIndex] = this.product.fileGallery[oldIndex];
                this.product.fileGallery[oldIndex] = originalFact;
            }
        }
    }

    private pushFileDown(file) {
        if (this.product.fileGallery != null && this.product.fileGallery.length > 0) {
            let oldIndex: number = this.product.fileGallery.indexOf(file);
            let newIndex: number = (oldIndex + 1);
            if (newIndex < this.product.fileGallery.length) {
                let originalFact = this.product.fileGallery[newIndex];
                this.product.fileGallery[newIndex] = this.product.fileGallery[oldIndex];
                this.product.fileGallery[oldIndex] = originalFact;
            }
        }
    }

    private removeFile(file) {
        let index: number = this.product.fileGallery.indexOf(file, 0);
        if (index > -1) {
            this.product.fileGallery.splice(index, 1);
            this.updateMaxFileGalleryTag();
        }
    }

    private sanitizeFacts(): void {
        for (let fact of this.product.facts) {
            delete fact["show"];
        }
    }

    private saveProduct(): void {
        this.sanitizeFacts();
        this.productService.updateProduct(this.product).subscribe(
            data => {
                this.product = data;
                this.renderDescriptions = true;
                this.updateDescriptionsTag();
                this.updateMaxFactsEntriesTag();
                this.updateMaxFileGalleryTag();
                this.info = "Erfolgreich gespeichert!";
            },
            err => {
                let errorMessage: string = err.toString();

                if (errorMessage.indexOf("401") !== -1) {
                    this.userService.logout();
                } else if (errorMessage.indexOf("400") !== -1) {
                    this.error = errorMessage;
                }
            });
    }

}
