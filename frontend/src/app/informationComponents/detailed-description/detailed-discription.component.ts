import {Component} from "@angular/core";
import {VendorService} from "../main/vendor.service";

@Component
({
    selector: 'vendor-detailed-discription',
    templateUrl: './detailed-discription.component.html'
})

export class DetailedDiscriptionComponent
{
    title: string = 'Detailbeschreibung';
    description: string = 'Zur Bearbeitung der Inhalte in der Detailbeschreibung'
    ckeditorContent: string = `<p>My HTML</p>`;


    constructor(private vendorService: VendorService)
    {
    }

    ngOnInit(): void
    {
    }

}