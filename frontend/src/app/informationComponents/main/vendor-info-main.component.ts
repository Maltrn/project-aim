import {Component, OnInit} from "@angular/core";
import {VendorService} from "../main/vendor.service";
import { InfoDTO } from "../main/dto/infoDTO";

@Component
({
    selector: 'vendor-info-main',
    templateUrl: './view/vendor-info-main.component.html'
})

export class VendorInfoMain implements OnInit
{
    vendorName: string = 'Firmenname';
    vendor: InfoDTO;


    constructor(
        private vendorService: VendorService
    ) { }

    ngOnInit(): void {
        this.vendorService.loadMock();
        //this.vendorService.loadVendorInformation();
        this.vendorName = this.vendorService.getVendorInformationDto().name; // Mock
        console.log("Vendor-Info-Mail aufruf"); // Zum debuggen
    }

}