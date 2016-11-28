import {Component, OnInit} from "@angular/core";
import { Router } from '@angular/router';
import {VendorService} from "../main/vendor.service";
import { InfoDTO } from "../main/dto/infoDTO";
import { Fact } from "../main/model/fact";

@Component
({
    selector: 'vendor-notes',
    templateUrl: './facts.component.html'
})
export class FactsTableComponent implements OnInit
{
    title: string = 'Faktentabelle';
    description: string = 'Zur Bearbeitung der Inhalte in der Faktentabelle';
    //facts: Fact[];  // Facts gibts es nicht mehr
    // demnächst das hier einbauen:  private _facts: [string, string];
    vendor: InfoDTO;
    selectedFact: Fact;

    constructor(
        private router: Router,
        private vendorService: VendorService
    ) { }

    ngOnInit(): void {
        // this.getVendor();
        //this.fillFacts();
    }

    getVendor(): void {
       this.vendorService.getVendor().then(vendor => this.vendor = vendor);
    }

    fillFacts(): void {
        this. facts = this.vendor.facts;
    }

}