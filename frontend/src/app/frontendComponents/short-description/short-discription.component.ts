import {Component} from "@angular/core";

@Component
({
    selector: 'vendor-short-discription',
    templateUrl: './short-discription.component.html'
})

export class ShortDiscriptionComponent
{
    title: string = 'Kurzbeschreibung';
    description: string = 'Zur Bearbeitung der Inhalte in der Kurzbeschreibung';
}