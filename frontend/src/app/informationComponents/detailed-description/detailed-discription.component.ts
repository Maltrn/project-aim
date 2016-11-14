/**
 * Created by Malte Scheller on 31.10.2016.
 */
import {Component} from "@angular/core";

@Component
({
    selector: 'vendor-detailed-discription',
    templateUrl: './detailed-discription.component.html'
})

export class DetailedDiscriptionComponent
{
    title: string = 'Detailbeschreibung';
    description: string = 'Zur Bearbeitung der Inhalte in der Detailbeschreibung'
}