import {Component} from "@angular/core";
import {VendorService} from "../main/vendor.service";

@Component
({
    selector: 'vendor-detailed-discription',
    templateUrl: './detailed-discription.component.html'
})

export class DetailedDiscriptionComponent {
    title: string = 'Detailbeschreibung';
    description: string = 'Zur Bearbeitung der Inhalte in der Detailbeschreibung'
    ckeditorContent: string = `<p>My HTML</p>`;


    constructor(
        private vendorService: VendorService
    ) { }

    ngOnInit(): void {
        this.vendorService.loadMock();
        //this.vendorService.loadVendorInformation();
        this.ckeditorContent = this.vendorService.getVendorInformationDto().longDescription; // Mock
        console.log("Detailed description call"); // Zum debuggen
    }

}