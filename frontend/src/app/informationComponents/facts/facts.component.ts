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
    facts: Fact[];
    vendor: InfoDTO;
    selectedFact: Fact;

    constructor(
        private router: Router,
        private vendorService: VendorService
    ) { }

    ngOnInit(): void {
        console.log("FactsTableComponent loaded"); // Zum debuggen
        this.vendor = this.vendorService.getVendorInformationDto();
        this.facts = this.vendor.facts;
    }

    onSelect(fact: Fact): void {
        this.selectedFact = fact;
        console.log("onSelect on Fact " + fact.name + " pressed");
    }

    delete(fact: Fact): void { // Sollen bei uns einzelne Fakten löschbar sein?
        // this.vendorService
        //     .delete(fact)
        //     .then(() => {
                 this.facts = this.facts.filter(f => f !== fact);
                if (this.selectedFact === fact) { this.selectedFact = null;
                 }
    // });
        console.log("delete on Fact " + fact.name + " pressed");
    }

    add(name: string, description: string): void {
        var fact = new Fact();
        name = name.trim();
        fact.name = name;
        fact.description = description;
        // if (!name) { return; }
        // this.vendorService.addFact(fact)
        //     .then(
        //fact => {
                this.facts.push(fact);   // Hiermit wird der erstellte Fact auf das Array facts gepusht
                this.selectedFact = null;
           // };
        // );
        console.log("add Fact name:'" + fact.name + "', description: '" + fact.description + "'");
    }
}