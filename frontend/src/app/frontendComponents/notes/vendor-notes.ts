import {Component} from "@angular/core";

@Component
({
    selector: 'vendor-notes',
    templateUrl: './vendor-notes.html'
})

export class FactsTableComponent
{
    title: string = 'Faktentabelle';
    description: string = 'Zur Bearbeitung der Inhalte in der Faktentabelle'
}