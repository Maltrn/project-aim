import {Component} from "@angular/core";
import {VendorService} from "../main/vendor.service";

@Component
({
    selector: 'vendor-detailed-discription',
    templateUrl: './detailed-description.component.html'
})

export class DetailedDescriptionComponent
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
